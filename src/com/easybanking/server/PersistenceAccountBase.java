package com.easybanking.server;

import com.google.appengine.api.datastore.Query;
import com.google.code.twig.ObjectDatastore;
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
    for (Account account : accounts) {
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
    yourAccount.setBalance(yourAccount.getBalance() - amount);
    theirAccount.setBalance(theirAccount.getBalance() + amount);
    datastore.get().update(yourAccount);
    datastore.get().update(theirAccount);
    Transaction transaction = new Transaction(currentUser.get().getId(), yourAccountNumber, theirAccountNumber, yourAccount.getBalance() - amount, yourAccount.getBalance(), yourAccount.getCurrency());
    datastore.get().store(transaction);
    return transaction;
  }

  @Override
  public List<Transaction> loadInTransactions() {
    return datastore.get().find().type(Transaction.class)
            .addFilter("yourId", Query.FilterOperator.EQUAL, currentUser.get().getId())
            .addFilter("inOrOut", Query.FilterOperator.EQUAL, 1).returnAll().now();
  }

  @Override
  public List<Transaction> loadOutTransactions() {
    return datastore.get().find().type(Transaction.class)
            .addFilter("yourId", Query.FilterOperator.EQUAL, currentUser.get().getId())
            .addFilter("inOrOut", Query.FilterOperator.EQUAL, 0).returnAll().now();
  }

  @Override
  public void saveContact(String name, String accountName) {
    Contact contact = new Contact();
    contact.setYourId(currentUser.get().getId());
    contact.setName(name);
    contact.setAccountNumber(accountName);
    datastore.get().store(contact);
  }

  @Override
  public List<Contact> loadContacts() {
    return datastore.get().find().type(Contact.class).addFilter("yourId", Query.FilterOperator.EQUAL, currentUser.get().getId()).returnAll().now();
  }

  @Override
  public List<Bill> loadBills() {
    return datastore.get().find().type(Bill.class).addFilter("yourId", Query.FilterOperator.EQUAL, currentUser.get().getId()).returnAll().now();
  }

  @Override
  public Bill addBill(String billName, String provider, String contractNumber, String account) {
    Bill bill = new Bill(currentUser.get().getId(), billName, provider, contractNumber, account);
    datastore.get().store(bill);
    return bill;
  }

  @Override
  public void deleteBill(String contractNumber) {
    Bill bill = datastore.get().find().type(Bill.class).addFilter("contractNumber", Query.FilterOperator.EQUAL, contractNumber).returnUnique().now();
    datastore.get().delete(bill);
  }


  //delete contact
  //delete bill
}
