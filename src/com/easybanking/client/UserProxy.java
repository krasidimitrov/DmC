package com.easybanking.client;

import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.easybanking.server.User;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ProxyFor(value = User.class, locator = PersistenceLongIdObjectLocator.class)
public interface UserProxy {

  public Long getId();

  public void setId(Long id);

  public Integer getVersion();

  public void setVersion(Integer version);

  public String getUsername();

  public void setUsername(String username);

  public String getPassword();


  public String getEmail();

  public void setEmail(String email);
}
