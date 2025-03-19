package com.azathoth.OLRResidency_Indigency.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomOauth2User implements OAuth2User {
    private final OAuth2User oAuth2User;
    private final Set<String> roles;

    public CustomOauth2User(OAuth2User oauth2User, Set<String> roles) {
        this.oAuth2User = oauth2User;
        this.roles = roles;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> (
                    GrantedAuthority) () -> role)
                    .collect(Collectors.toList()
                );
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }
}
