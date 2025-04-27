package com.tcc.tcc.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.tcc.tcc.domain.dto.user.UserResponseDTO;
import com.tcc.tcc.domain.exception.UnsuccessfulRequestException;
import com.tcc.tcc.domain.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private JwtUtil jwtUtil;
    private UserDetailsSecurityService userDetailsSecurityService;

    @Autowired
    private UserService userService;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserDetailsSecurityService userDetailsSecurityService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsSecurityService = userDetailsSecurityService;
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private boolean checkEmails(String userEmail, String uri){
        final String id = uri.substring(uri.lastIndexOf('/')+1);
        final UserResponseDTO userResponseDTO = userService.findUserById(id);
        return (userResponseDTO.getEmail().equals(userEmail));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException, UnsuccessfulRequestException{
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        final String userEmail = jwtUtil.extractUsername(jwt);
        final String uri = request.getRequestURI();
        
        if(request.getMethod() != "POST" /*&& uri.contains("/user/")*/ && !checkEmails(userEmail, uri)){
            // throw new UnsuccessfulRequestException("The e-mail provided does not matches the e-mail assigned to the authentication token!");
            chain.doFilter(request, response);
            return;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (userEmail != null && authentication == null) {
            UserDetails userDetails = this.userDetailsSecurityService.loadUserByUsername(userEmail);
            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}
