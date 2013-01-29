package com.easybanking.client;

import com.easybanking.server.Account;
import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ProxyFor(value = Account.class, locator = PersistenceLongIdObjectLocator.class)
public interface AccountProxy extends EntityProxy {


  public Long getId();

  public Integer getVersion();

  public void setId(Long id);

  public String getCurrency();

  public void setCurrency(String currency);

  public Double getInterest();

  public void setInterest(Double interest);

  public String getNumber();

  public void setNumber(String number);

  public Double getBalance();

  public void setBalance(Double balance);
}
