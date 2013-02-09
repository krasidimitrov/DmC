package com.easybanking.server;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ImplementedBy(PersistenceAccountBase.class)
public interface AccountBase {
  List<String> loadAccountNumbers();

  Account loadAccountByNumber(String number);

  List<Account> loadAccounts();

  Transaction sendMoney(double amount, String yourAccountNumber, String theirAccountNumber);

  List<Transaction> loadInTransactions();

  List<Transaction> loadOutTransactions();

  void saveContact(String name, String accountName);

  List<Contact> loadContacts();

  List<Bill> loadBills();

  Bill addBill(String billName, String yourAccountNumber, String contractNumber);

  void deleteBill(String contractNumber);
}
