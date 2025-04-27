package com.tcc.tcc.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tcc.tcc.domain.model.User;
import com.tcc.tcc.domain.repository.UserRepository;

@Component
public class UserDetailsSecurityService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmail(username);
        if(optUser.isEmpty()){
            throw new UsernameNotFoundException("Invalid email or password");
        }
        return optUser.get();
    }
    
}
