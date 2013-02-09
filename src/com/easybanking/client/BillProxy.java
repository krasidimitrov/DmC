package com.easybanking.client;

import com.easybanking.server.Bill;
import com.easybanking.server.PersistenceLongIdObjectLocator;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ProxyFor(value = Bill.class, locator = PersistenceLongIdObjectLocator.class)
public interface BillProxy extends EntityProxy {

  public Long getId();

  public Integer getVersion();

  public Long getYourId();

  public void setYourId(Long yourId);

  public String getBillName();

  public void setBillName(String billName);

  public String getYourAccountNumber();

  public void setYourAccountNumber(String yourAccountNUmber);

  public String getContractNumber();

  public void setContractNumber(String contractNumber);

  String getProvider();

  void setProvider(String provider);
}
