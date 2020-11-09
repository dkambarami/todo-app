package com.delvin.rest.webservices.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "xxxx3",
        "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "xxxx1",
        "$2a$10$R8xEMrOPThFIbmJoK3lta.Nq5sHRo.LNX8MBau7F0LP4AYL5ffA7e", "ROLE_USER_2"));
    inMemoryUserList.add(new JwtUserDetails(2L, "xxxx4",
        "$2a$10$R8xEMrOPThFIbmJoK3lta.Nq5sHRo.LNX8MBau7F0LP4AYL5ffA7e", "ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}

