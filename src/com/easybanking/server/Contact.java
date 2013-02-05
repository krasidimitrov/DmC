package com.easybanking.server;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class Contact implements Entity {

  private Long id;

  private Long yourId;

  private String name;

  private String accountNumber;

  @Override
  public Object getId() {
    return id;
  }

  @Override
  public Integer getVersion() {
    return 0;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getYourId() {
    return yourId;
  }

  public void setYourId(Long yourId) {
    this.yourId = yourId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
}
