package com.taskmanager.configuration.security;

import com.taskmanager.configuration.security.filter.CsrfCookieFilter;
import com.taskmanager.configuration.security.filter.JWTTokenGeneratorFilter;
import com.taskmanager.configuration.security.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static com.taskmanager.constants.ApplicationConstants.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName(CSRF_REQUEST_ATTRIBUTE_NAME);
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(List.of(ANGULAR_APP_ORIGIN));
                    corsConfiguration.setAllowedMethods(List.of(ALL));
                    corsConfiguration.setAllowCredentials(true);
                    corsConfiguration.setAllowedHeaders(List.of(ALL));
                    corsConfiguration.setExposedHeaders(List.of(AUTHORIZATION));
                    corsConfiguration.setMaxAge(MAX_AGE);
                    return corsConfiguration;
                })).csrf((csrf) -> csrf.csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                        .ignoringRequestMatchers(PROJECTS_API_URL, CLIENT_LOCATIONS_API_URL)
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .headers(headers -> headers.xssProtection(xss ->
                                xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                        .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'")))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                //.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                //.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                //.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(HttpMethod.POST, PROJECTS_API_URL).authenticated()
                        .requestMatchers(HttpMethod.PUT, PROJECTS_API_URL).authenticated()
                        .requestMatchers(HttpMethod.DELETE, PROJECTS_API_URL).authenticated()
                        .requestMatchers(HttpMethod.GET, PROJECTS_API_SEARCH_URL).authenticated()
                        .requestMatchers(HttpMethod.GET, PROJECTS_API_URL).authenticated()
                        .requestMatchers(HttpMethod.GET, CLIENT_LOCATIONS_API_URL).authenticated()
                        .requestMatchers(HttpMethod.GET, USER_API_URL).authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
