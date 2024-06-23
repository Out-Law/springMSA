package com.example.serverjwt.services;

import com.example.serverjwt.domain.JwtAuthentication;
import com.example.serverjwt.domain.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<?> roles = claims.get("roles", List.class);
        return roles.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
