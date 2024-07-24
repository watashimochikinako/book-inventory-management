package com.example.demo.infrastructure.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.application.AuthenticationProvider;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Override
    public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
