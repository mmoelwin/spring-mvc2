package com.demo.springmvc.service;

import com.demo.springmvc.model.Role;
import com.demo.springmvc.model.User;
import com.demo.springmvc.repository.RoleRepository;
import com.demo.springmvc.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetailsServiceImpl (UserRepository userRepository, RoleRepository roleRepository,
                                   BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
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

    public User register(User user){
        Role role=roleRepository.findByName("ROLE_admin");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.addRole(role);
        role.getUsers().add(user);
        return userRepository.save(user);
    }
}
