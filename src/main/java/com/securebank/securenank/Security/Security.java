package com.securebank.securenank.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity htt) throws Exception {
        htt.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.
                            requestMatchers(HttpMethod.POST, "/account").permitAll()
                            .requestMatchers(HttpMethod.POST, "login").permitAll()
                            .anyRequest()
                            .authenticated();
                });
        return htt.build();
    }
}
