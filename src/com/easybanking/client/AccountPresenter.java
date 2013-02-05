package com.easybanking.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

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
    requestFactory.accountRequest().loadAccountNumbers().to(new Receiver<List<String>>() {
      @Override
      public void onSuccess(List<String> response) {
        accountView.loadAccounts(response);
      }
    }).fire();
  }


  public void fillAccountInfo(String accountNumber) {
    requestFactory.accountRequest().loadAccountByNumber(accountNumber).to(new Receiver<AccountProxy>() {
      @Override
      public void onSuccess(AccountProxy response) {
        accountView.renderAccountInfo(response);
      }
    }).fire();
  }
}
