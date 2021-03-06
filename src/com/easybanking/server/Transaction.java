package com.easybanking.server;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class Transaction implements Entity {

  private Long id;

  private Long yourId;
  //0 out 1 in
  private int inOrOut;

  private String yourAccountNumber;

  private String theirAccountNumber;

  private double amount;

  private double balanceAfterTransaction;

  private String currency;

  public Transaction(Long yourId, String yourAccountNumber, String theirAccountNumber, double amount, double balanceAfterTransaction, String currency){

    this.yourId = yourId;
    this.yourAccountNumber = yourAccountNumber;
    this.theirAccountNumber = theirAccountNumber;
    this.amount = amount;
    this.balanceAfterTransaction = balanceAfterTransaction;
    this.currency = currency;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public Integer getVersion() {
    return 0;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getInOrOut() {
    return inOrOut;
  }

  public void setInOrOut(int inOrOut) {
    this.inOrOut = inOrOut;
  }

  public String getYourAccountNumber() {
    return yourAccountNumber;
  }

  public void setYourAccountNumber(String yourAccountNumber) {
    this.yourAccountNumber = yourAccountNumber;
  }

  public String getTheirAccountNumber() {
    return theirAccountNumber;
  }

  public void setTheirAccountNumber(String theirAccountNumber) {
    this.theirAccountNumber = theirAccountNumber;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getBalanceAfterTransaction() {
    return balanceAfterTransaction;
  }

  public void setBalanceAfterTransaction(double balanceAfterTransaction) {
    this.balanceAfterTransaction = balanceAfterTransaction;
  }

  public Long getYourId() {
    return yourId;
  }

  public void setYourId(Long yourId) {
    this.yourId = yourId;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }
}
