package com.easybanking.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class TransferPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private TransferView transferView;

  public TransferPresenter(BankRequestFactory requestFactory, TransferView transferView) {
    this.requestFactory = requestFactory;
    this.transferView = transferView;
    this.transferView.setPresenter(this);
    fillInitialData();
  }

  public void fillInitialData(){
    BankRequestFactory.AccountRequest accountRequest = requestFactory.accountRequest();

    accountRequest.loadAccountNumbers().to(new Receiver<List<String>>() {
      @Override
      public void onSuccess(List<String> response) {
        transferView.renderInitialData(response);
      }
    }).fire();

  }

  public void fillAccountData(String accountNumber) {
    requestFactory.accountRequest().loadAccountByNumber(accountNumber).to(new Receiver<AccountProxy>() {
      @Override
      public void onSuccess(AccountProxy response) {
        transferView.renderAccountDetails(response);
      }
    }).fire();
  }
}
