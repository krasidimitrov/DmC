package com.easybanking.server;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Account implements Entity {

  private Long id;

  private String currency;

  private Double interest;

  private String number;

  private Double balance;

  private Long userId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Account(){

  }

  public Account(Long userId, String number, double balance, String currency, double interest){
    this.userId = userId;
    this.number = number;
    this.balance = balance;
    this.currency = currency;
    this.interest = interest;
  }

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
