package com.example.paypalserver.controllers;

import com.paypal.api.payments.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import com.example.paypalserver.services.PaypalService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    private final PaypalService paypalService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/payment/create")
    public RedirectView createPay(){
        try{
            String cancelUrl = "https://localhost:8080/paypal/cancel";
            String successUrl = "https://localhost:8080/paypal/success";
            Payment payment = paypalService.createPayment(
                    10.0,
                    "USD",
                    "paypal",
                    "sale",
                    "Payment description",
                    cancelUrl,
                    successUrl
            );

            payment.getLinks()
                    .stream()
                    .filter(p -> p.getRel().equals("approve_url"))
                    .findFirst().ifPresent(p -> {
                        new RedirectView(p.getHref());
                    });
        }
        catch (com.paypal.base.rest.PayPalRESTException e){
            log.error(e.getMessage());
        }

        return new RedirectView("/payment/error");
    }

    @GetMapping("paypal/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("payerId") String payerId
    ){
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "paymentSuccess";
            }
        } catch (com.paypal.base.rest.PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "paymentSuccess";
    }

    @GetMapping("paypal/cancel")
    public String paymentCancel(){
        return "paymentCancel";
    }

    @GetMapping("paypal/error")
    public String paymentError(){
        return "paymentError";
    }
}
