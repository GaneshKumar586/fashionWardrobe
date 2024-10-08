package com.gana.Service;

import com.gana.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;
    public CustomUserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.gana.model.User user = userRepository.findUserByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("user not found with provided email"+username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(user.getEmail(), user.getPassword(),authorities);
    }
}
