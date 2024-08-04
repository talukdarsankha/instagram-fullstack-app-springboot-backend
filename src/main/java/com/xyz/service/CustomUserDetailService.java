package com.xyz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xyz.Models.User;
import com.xyz.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> opt = userRepository.findByEmail(username);
		if (opt.isPresent()) {
			User user = opt.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);    
		}
		throw new BadCredentialsException("User Not Found by this email :"+username);
	}
	
	

}
