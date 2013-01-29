package com.easybanking.client;

import com.easybanking.inject.MyServiceLocator;
import com.easybanking.server.AccountService;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface BankRequestFactory extends RequestFactory {

  @Service(value = AccountService.class, locator = MyServiceLocator.class)
  public interface AccountRequest extends RequestContext {

    Request<Void> test();

    Request<List<String>> getAccountNumbersByName(String username);

    Request<AccountProxy> getAccount(String username, String accountNumber);

//    Request<List<ProductProxy>> getAllProducts();
//
//    Request<Void> update(ProductProxy productProxy);
  }

  AccountRequest accountRequest();

}
