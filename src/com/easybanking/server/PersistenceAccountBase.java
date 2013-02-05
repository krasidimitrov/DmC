package com.easybanking.server;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.code.twig.ObjectDatastore;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersistenceAccountBase implements AccountBase {


  private final Provider<ObjectDatastore> datastore;
  private final Provider<User> currentUser;

  @Inject
  public PersistenceAccountBase(Provider<ObjectDatastore> datastore, Provider<User> currentUser) {
    this.datastore = datastore;
    this.currentUser = currentUser;
  }

  @Override
  public List<String> loadAccountNumbers() {
    List<Account> accounts = datastore.get().find().type(Account.class)
            .addFilter("userId", Query.FilterOperator.EQUAL, currentUser.get().getId()).returnAll().now();
    List<String> stringAccounts = new ArrayList<String>();
    for(Account account : accounts){
      stringAccounts.add(account.getNumber());
    }
    return stringAccounts;
  }

  @Override
  public Account loadAccountByNumber(String number) {
    return datastore.get().find().type(Account.class).addFilter("number", Query.FilterOperator.EQUAL, number).returnUnique().now();
  }

  @Override
  public List<Account> loadAccounts() {
    return datastore.get().find().type(Account.class).addFilter("userId", Query.FilterOperator.EQUAL, currentUser.get().getId()).returnAll().now();
  }

  @Override
  public Transaction sendMoney(double amount, String yourAccountNumber, String theirAccountNumber) {
    Account yourAccount = loadAccountByNumber(yourAccountNumber);
    Account theirAccount = loadAccountByNumber(theirAccountNumber);
    yourAccount.setBalance(yourAccount.getBalance()-amount);
    theirAccount.setBalance(theirAccount.getBalance()+amount);
    datastore.get().update(yourAccount);
    datastore.get().update(theirAccount);
    Transaction transaction = new Transaction(currentUser.get().getId(), yourAccountNumber, theirAccountNumber, yourAccount.getBalance()-amount, yourAccount.getBalance(), yourAccount.getCurrency());
    datastore.get().store(transaction);
    return transaction;
  }

  //loadTransaction 0
  //loadTransaction 1
  //save contact
  //load contactAccountsNumbers

}
