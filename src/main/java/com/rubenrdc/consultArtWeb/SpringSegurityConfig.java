package com.rubenrdc.consultArtWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 *
 * @author Ruben
 */
@Configuration
public class SpringSegurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.GET, "/api/articulos", "/api/articulos/{codigo}","api/depositos","api/depositos/{cod}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/articulos", "/api/articulos/{codigo}","api/depositos","api/depositos/{cod}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/articulos", "/api/articulos/{codigo}","api/depositos","api/depositos/{cod}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/articulos", "/api/articulos/{codigo}","api/depositos","api/depositos/{cod}").permitAll()
                .requestMatchers(HttpMethod.GET, "/login").permitAll()
                //.requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                //.requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                //.requestMatchers(HttpMethod.GET, "/api/products", "/api/products/{id}").hasAnyRole("ADMIN", "USER")
                //.requestMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
                //.requestMatchers(HttpMethod.PUT, "/api/products/{id}").hasRole("ADMIN")
                //.requestMatchers(HttpMethod.DELETE, "/api/products/{id}").hasRole("ADMIN")
                .anyRequest().authenticated()
        )
                .csrf(config -> config.disable())
                .build();
    }
}
