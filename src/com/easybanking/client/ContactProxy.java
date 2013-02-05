package com.easybanking.client;

import com.easybanking.server.Contact;
import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ProxyFor(value = Contact.class, locator = PersistenceLongIdObjectLocator.class)
public interface ContactProxy extends EntityProxy {

  public Object getId();

  public Integer getVersion();

  public Long getYourId();

  public void setYourId(Long yourId);

  public String getName();

  public void setName(String name);

  public String getAccountNumber();

  public void setAccountNumber(String accountNumber);
}
