package com.easybanking.client;

import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.easybanking.server.User;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ProxyFor(value = User.class, locator = PersistenceLongIdObjectLocator.class)
public interface UserProxy extends EntityProxy {

  public Long getId();

  public void setId(Long id);

  public Integer getVersion();

  public void setVersion(Integer version);

  public String getUsername();

  public void setUsername(String username);

  public String getPassword();

  public void setPassword(String password);

  public String getEmail();

  public void setEmail(String email);
}
