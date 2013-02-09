package com.easybanking.server;

import com.google.code.twig.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class Account implements Entity {

  @Id
  private Long id;

  private String currency;

  private double interest;

  private String number;

  private double balance;

  private Long userId;

  private int interestInterval;

  private List<String> cardNumbers = new ArrayList<String>();

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Account(){

  }

  public Account(Long userId, String number, double balance, String currency, double interest, int interestInterval){
    this.userId = userId;
    this.number = number;
    this.balance = balance;
    this.currency = currency;
    this.interest = interest;
    this.interestInterval = interestInterval;
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

  public double getInterest() {
    return interest;
  }

  public void setInterest(double interest) {
    this.interest = interest;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public int getInterestInterval() {
    return interestInterval;
  }

  public void setInterestInterval(int interestInterval) {
    this.interestInterval = interestInterval;
  }

  public List<String> getCardNumbers() {
    return cardNumbers;
  }

  public void setCardNumbers(List<String> cardNumbers) {
    this.cardNumbers = cardNumbers;
  }
}
