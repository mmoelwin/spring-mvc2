package com.demo.springmvc.service;

import com.demo.springmvc.model.User;
import com.demo.springmvc.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    public UserDetailsServiceImpl (UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> user=userRepository.findByEmail(email);
//        if (!user.isPresent()){
//            throw  new UsernameNotFoundException(email + " Not found");
//        }
//        return user.get();

        return userRepository
        .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + " Not found"));

    }
}
