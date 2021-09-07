package com.example.SmartContactManager.config;

import com.example.SmartContactManager.dao.UserRepository;
import com.example.SmartContactManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // FETCHING USER FROM DATABASE

        User user = userRepository.getUserByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("Could not find user.");
        }

        CustomUserDetail customUserDetail = new CustomUserDetail(user);

        return customUserDetail;
    }
}
