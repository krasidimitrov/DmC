package com.easybanking.client;

import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private AccountView accountView;

  private UserProvider userProvider;

  public AccountPresenter(BankRequestFactory requestFactory, AccountView accountView, UserProvider userProvider) {

    this.requestFactory = requestFactory;
    this.accountView = accountView;
    this.userProvider = userProvider;
    this.accountView.setPresenter(this);
    fillAccountNumbers();
  }

  public void fillAccountNumbers(){
    requestFactory.accountRequest().getAccountNumbersByName(userProvider.getUsername()).to(new Receiver<List<String>>() {
      @Override
      public void onSuccess(List<String> strings) {
        accountView.fillAccountNumbers(strings);
      }
    }).fire();
  }

  public void setAccountInfo(String accountNumber){

    requestFactory.accountRequest().getAccount(userProvider.getUsername(), accountNumber).to(new Receiver<AccountProxy>() {
      @Override
      public void onSuccess(AccountProxy accountProxy) {
        Window.alert(accountProxy.getNumber() +"-----" +String.valueOf(accountProxy.getBalance())+"-----" +accountProxy.getCurrency()+"-----" +String.valueOf(accountProxy.getInterest()));
      }

      @Override
      public void onFailure(ServerFailure error) {
        super.onFailure(error);
      }
    }).fire();

  }
}
