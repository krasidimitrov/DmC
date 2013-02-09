package com.easybanking.client;

import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.easybanking.server.Transaction;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ProxyFor(value = Transaction.class, locator = PersistenceLongIdObjectLocator.class)
public interface TransactionProxy extends EntityProxy {
  public Long getId();

  public Integer getVersion();

  public void setId(Long id);

  public int getInOrOut();

  public void setInOrOut(int inOrOut);

  public String getYourAccountNumber();

  public void setYourAccountNumber(String yourAccountNumber);

  public String getTheirAccountNumber();

  public void setTheirAccountNumber(String theirAccountNumber);

  public double getBalanceAfterTransaction();

  public void setBalanceAfterTransaction(double balanceAfterTransaction);

  public Long getYourId();

  public void setYourId(Long yourId);

  public String getCurrency();

  public void setCurrency(String currency);

  public double getAmount();

  public void setAmount(double amount);

}
