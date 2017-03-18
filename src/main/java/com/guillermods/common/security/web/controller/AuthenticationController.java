package com.guillermods.common.security.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.guillermods.common.security.config.SecurityConstants;
import com.guillermods.common.security.domain.User;
import com.guillermods.common.security.jwt.TokenGenerator;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenGenerator tokenGenerator;

  @Autowired
  @Qualifier("userDetailsServiceImpl")
  private UserDetailsService userDetailsService;


  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> authenticationRequest(
      @RequestBody AuthenticationRequestDto authenticationRequest, Device device)
      throws AuthenticationException {
    
    Authentication authentication =
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUsername(), authenticationRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    String token = this.tokenGenerator.generateToken(userDetails, device);

    return ResponseEntity.ok(new AuthenticationResponseDto(token));
  }

  @RequestMapping(value = "refresh", method = RequestMethod.GET)
  public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
    String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
    String username = this.tokenGenerator.getUsernameFromToken(token);
    User user = (User) this.userDetailsService.loadUserByUsername(username);
    if (this.tokenGenerator.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = this.tokenGenerator.refreshToken(token);
      return ResponseEntity.ok(new AuthenticationResponseDto(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }


  @RequestMapping(value = "test", method = RequestMethod.POST)
  public ResponseEntity<AuthenticationResponseDto> test(
      @RequestBody AuthenticationRequestDto requestDto) {

    return ResponseEntity.ok(new AuthenticationResponseDto("1293847189234"));
  }

}
