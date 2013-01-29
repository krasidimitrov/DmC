package com.easybanking.server;

import com.google.inject.Inject;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountServiceImpl implements AccountService {

  private AccountRepository accountRepository;

  @Inject
  public AccountServiceImpl(AccountRepository repository) {
    this.accountRepository = repository;
  }

  @Override
  public void test() {
    accountRepository.createTestData();
  }

  @Override
  public List<String> getAccountNumbersByName(String username) {
    return accountRepository.getAccountNumbersByUser(username);
  }

  @Override
  public Account getAccount(String username, String number) {
    return accountRepository.getAccount(username, number);
  }
}
