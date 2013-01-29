package com.easybanking.server;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ImplementedBy(AccountRepositoryImpl.class)
public interface AccountRepository {
  public void createTestData();

  public List<String> getAccountNumbersByUser(String user);

  Account getAccount(String username, String number);
}
