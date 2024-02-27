package com.tatva.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.tatva.filter.CsrfCookieFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
		requestHandler.setCsrfRequestAttributeName("_csrf");

		http.securityContext((context) -> context.requireExplicitSave(false))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				///// CORS
				.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));// FE k baare m
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setMaxAge(3600L);// browser will remember this cpnf. upto 1 hour
						return config;
					}
				}))
				//////////////// CSRF
				.csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
						.ignoringRequestMatchers("/contact", "/register", "/text/tellme")
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
				//////////////////
				.authorizeHttpRequests((requests) -> requests
						.requestMatchers("/myAccount" ).authenticated()
						.requestMatchers("/register", "/comein", "/text/tellme").permitAll())
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
