package com.easybanking.client;

import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.easybanking.server.Transaction;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ProxyFor(value = Transaction.class, locator = PersistenceLongIdObjectLocator.class)
public interface TransactionProxy {
  public Object getId();

  public Integer getVersion();

  public void setId(Long id);

  public int getInOrOut();

  public void setInOrOut(int inOrOut);

  public String getYourAccountNumber();

  public void setYourAccountNumber(String yourAccountNumber);

  public String getTheirAccountNumber();

  public void setTheirAccountNumber(String theirAccountNumber);

  public double getBalanceBeforeTransaction();

  public void setBalanceBeforeTransaction(double balanceBeforeTransaction);

  public double getBalanceAfterTransaction();

  public void setBalanceAfterTransaction(double balanceAfterTransaction);

  public Long getYourId();

  public void setYourId(Long yourId);

  public String getCurrency();

  public void setCurrency(String currency);
}
