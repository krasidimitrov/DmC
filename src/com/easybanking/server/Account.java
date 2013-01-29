package com.easybanking.server;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Account {

  private Long id;

  private Long version;

  private String currency;

  private Double interest;

  private String number;

  private Double balance;

  public Account(String number, double balance, String currency, double interest){
    this.number = number;
    this.balance = balance;
    this.currency = currency;
    this.interest = interest;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Double getInterest() {
    return interest;
  }

  public void setInterest(Double interest) {
    this.interest = interest;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }
}
