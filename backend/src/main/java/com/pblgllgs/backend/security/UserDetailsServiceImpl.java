package com.pblgllgs.backend.security;
/*
 *
 * @author pblgl
 * Created on 10-04-2024
 *
 */

import com.pblgllgs.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//@Service
//@RequiredArgsConstructor
public class UserDetailsServiceImpl {//implements UserDetailsService {

//    private final UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("USERNAME_NOT_FOUND"));
//    }
}
