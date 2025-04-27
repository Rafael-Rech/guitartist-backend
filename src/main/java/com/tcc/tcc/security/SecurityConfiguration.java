package com.tcc.tcc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.tcc.tcc.domain.service.UserService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.modelmapper.ModelMapper;

@Configuration
public class SecurityConfiguration {
        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private AuthenticationConfiguration authenticationConfiguration;

        @Autowired
        private UserDetailsSecurityService userDetailsSecurityServer;

        @Autowired
        private ModelMapper mapper;

        @Autowired
        private UserService userService;

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable())
                                .cors(withDefaults()).csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.POST,
                                                "/api/user").permitAll().requestMatchers(HttpMethod.GET, "/api/user")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .sessionManagement(management -> management
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                http.addFilter(
                                new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtUtil,
                                                mapper));

                JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(
                                authenticationManager(authenticationConfiguration), jwtUtil,
                                userDetailsSecurityServer);
                if (jwtAuthorizationFilter.getUserService() == null) {
                        jwtAuthorizationFilter.setUserService(userService);
                }
                http.addFilter(jwtAuthorizationFilter);

                return http.build();
        }
}
