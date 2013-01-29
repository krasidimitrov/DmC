package com.easybanking.server;


import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ImplementedBy(AccountServiceImpl.class)
public interface AccountService {

  public void test();

  List<String> getAccountNumbersByName(String username);

  Account getAccount(String username, String number);

}
