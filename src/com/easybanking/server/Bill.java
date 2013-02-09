package com.easybanking.server;

import com.google.code.twig.annotation.Id;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class Bill implements Entity {

  @Id
  Long id;

  Long yourId;

  String billName;

  String provider;

  String yourAccountNumber;

  String contractNumber;

  public Bill() {
  }

  public Bill(Long yourId, String billName, String yourAccountNumber, String contractNumber){
    this.yourId = yourId;
    this.billName = billName;
    this.yourAccountNumber = yourAccountNumber;
    this.contractNumber = contractNumber;
  }

  public Bill(Long yourId, String billName, String provider, String contractNumber, String account) {
    this.yourId = yourId;
    this.billName = billName;
    this.provider = provider;
    this.contractNumber = contractNumber;
    this.yourAccountNumber = account;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public Integer getVersion() {
    return 0;
  }

  public Long getYourId() {
    return yourId;
  }

  public void setYourId(Long yourId) {
    this.yourId = yourId;
  }

  public String getBillName() {
    return billName;
  }

  public void setBillName(String billName) {
    this.billName = billName;
  }

  public String getYourAccountNumber() {
    return yourAccountNumber;
  }

  public void setYourAccountNumber(String yourAccountNumber) {
    this.yourAccountNumber = yourAccountNumber;
  }

  public String getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(String contractNumber) {
    this.contractNumber = contractNumber;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }
}
