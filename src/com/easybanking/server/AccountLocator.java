package com.easybanking.server;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountLocator extends Locator<Account, Long> {

  private AccountRepository repository = new AccountRepositoryImpl(DatastoreServiceFactory.getDatastoreService());


  @Override
  public Account create(Class<? extends Account> aClass) {
    try {
      return Account.class.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Account find(Class<? extends Account> aClass, Long aLong) {
    return null;
  }

  @Override
  public Class<Account> getDomainType() {
    return Account.class;
  }

  @Override
  public Long getId(Account account) {
    return account.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Object getVersion(Account account) {
    return account.getVersion();
  }
}
