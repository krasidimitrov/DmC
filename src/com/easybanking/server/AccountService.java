package com.easybanking.server;


import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AccountService {

  public void test();

  List<String> getAccountNumbersByName(String username);

  Account getAccount(String username, String number);

}
