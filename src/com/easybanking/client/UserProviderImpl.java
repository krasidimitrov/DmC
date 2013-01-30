package com.easybanking.client;

import com.google.gwt.user.client.Cookies;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class UserProviderImpl implements UserProvider {

  @Override
  public void setUsername(String username) {
    Cookies.setCookie("username", username);
  }

  @Override
  public String getUsername() {
    return "test@gmail.com";
  }
}
