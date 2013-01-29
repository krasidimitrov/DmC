package com.easybanking.client;

import com.easybanking.inject.MyServiceLocator;
import com.easybanking.server.AccountBase;
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

  }

  AccountRequest accountRequest();

}
