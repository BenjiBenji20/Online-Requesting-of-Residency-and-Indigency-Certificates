package com.azathoth.OLRResidency_Indigency.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // handles email value from application.properties
    @Value("${admin.email}")
    private String secretAdminEmail;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extract user details
        String email = oAuth2User.getAttribute("email");

        // Assign ROLE_ADMIN role to specific users
        Set<String> roles = new HashSet<>();
        if(secretAdminEmail.equals(email)) {
            roles.add("ROLE_ADMIN");
        }

        return new CustomOauth2User(oAuth2User, roles);
    }
}
