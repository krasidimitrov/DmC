package com.easybanking.client;

import com.easybanking.inject.MyServiceLocator;
import com.easybanking.server.AccountBase;
import com.easybanking.server.UserBase;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface BankRequestFactory extends RequestFactory {

  @Service(value = AccountBase.class, locator = MyServiceLocator.class)
  public interface AccountRequest extends RequestContext {

    Request<List<String>> loadAccountNumbers();

    Request<AccountProxy> loadAccountByNumber(String number);

    Request<List<AccountProxy>> loadAccounts();

    Request<TransactionProxy> sendMoney(double amount, String yourAccountNumber, String theirAccountNumber);

    Request<List<TransactionProxy>> loadInTransactions();

    Request<List<TransactionProxy>> loadOutTransactions();

    Request<List<BillProxy>> loadBills();

    Request<BillProxy> addBill(String billName, String provider, String contractNumber, String account);

    Request<Void> deleteBill(Long billId);

  }

  AccountRequest accountRequest();

  @Service(value = UserBase.class, locator = MyServiceLocator.class)
  public interface LoginRequest extends RequestContext {
    Request<UserProxy> loginViaSession();

    Request<UserProxy> login(String username, String password);

    Request<Void> logout();
  }

  LoginRequest getLoginRequest();
}
