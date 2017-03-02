package com.guillermods.common.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guillermods.common.security.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return (UserDetails) userRepository.findUserByUsername(username);
  }


}
