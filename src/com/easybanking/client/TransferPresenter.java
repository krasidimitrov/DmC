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

   //on page open load all your account numbers and any other information the page need to be visualized
  public void fillInitialData(){
    BankRequestFactory.AccountRequest accountRequest = requestFactory.accountRequest();

    accountRequest.loadAccountNumbers().to(new Receiver<List<String>>() {
      @Override
      public void onSuccess(List<String> response) {
        transferView.renderInitialData(response);
      }
    }).fire();

  }

  //when you select account number from the drop menu the balance and currency of the account is laoded
  public void fillAccountData(String accountNumber) {
    requestFactory.accountRequest().loadAccountByNumber(accountNumber).to(new Receiver<AccountProxy>() {
      @Override
      public void onSuccess(AccountProxy response) {
        transferView.renderAccountDetails(response);
      }
    }).fire();
  }

  //load outgoing and incoming transactions (few of both types)
  public void fillTransactionHistory(){
    BankRequestFactory.AccountRequest accountRequest = requestFactory.accountRequest();

    accountRequest.loadInTransactions().to(new Receiver<List<TransactionProxy>>() {
      @Override
      public void onSuccess(List<TransactionProxy> response) {
        //visualize incoming transaction
      }
    });

    accountRequest.loadOutTransactions().to(new Receiver<List<TransactionProxy>>() {
      @Override
      public void onSuccess(List<TransactionProxy> response) {
        //visualize outgoing transaction
      }
    }).fire();
  }
}
