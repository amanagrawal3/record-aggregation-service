package com.aman.domain.common;

import com.aman.domain.entity.User;
import com.aman.domain.common.SecurityUser;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Convert our user object to spring security user object
 */
public class UserFactory {

  public static SecurityUser create(User user) {
    return new SecurityUser(
      user.getId(),
      user.getUsername(),
      user.getPassword(),
      user.getEmail(),
      user.getLastPasswordReset(),
      AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities())
    );
  }

}
