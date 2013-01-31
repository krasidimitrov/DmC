package com.easybanking.client.gin;


import com.easybanking.client.*;
import com.easybanking.client.login.Login;
import com.easybanking.client.login.LoginView;
import com.easybanking.client.login.LoginViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class SimpleGinModule extends AbstractGinModule{
  @Override
  protected void configure() {
    bind(TransferView.class).to(TransferViewImpl.class).in(Singleton.class);
    bind(AccountView.class).to(AccountViewImpl.class).in(Singleton.class);
    bind(CreditView.class).to(CreditViewImpl.class).in(Singleton.class);
    bind(CalculatorView.class).to(CalculatorViewImpl.class).in(Singleton.class);
    bind(UserProvider.class).to(UserProviderImpl.class).in(Singleton.class);
    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
  }

  @Provides
  @Singleton
  BankRequestFactory getBankRequestFactory(EventBus eventBus) {
    BankRequestFactory bankRequestFactory = GWT.create(BankRequestFactory.class);
    bankRequestFactory.initialize(eventBus);
    return bankRequestFactory;
  }

  @Provides
  @Singleton
  LoginView getLoginView(BankRequestFactory requestFactory) {

    LoginView loginView = new LoginViewImpl();
    Login login = new Login(loginView, requestFactory);
    loginView.setPresenter(login);

    return loginView;
  }
}
