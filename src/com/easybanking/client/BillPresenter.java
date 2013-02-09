package com.easybanking.client;

import com.google.gwt.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class BillPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private BillView billView;
  private EventBus eventBus;

  public BillPresenter(BankRequestFactory requestFactory, BillView billView, EventBus eventBus) {
    this.requestFactory = requestFactory;
    this.billView = billView;
    this.billView.setPresenter(this);
    this.eventBus = eventBus;
    loadInitialData();
  }

  public void loadInitialData() {
    BankRequestFactory.AccountRequest accountRequest = requestFactory.accountRequest();


    accountRequest.loadBills().to(new Receiver<List<BillProxy>>() {
      @Override
      public void onSuccess(List<BillProxy> response) {
        billView.renderBills(response);
      }
    });

    accountRequest.loadAccountNumbers().to(new Receiver<List<String>>() {
      @Override
      public void onSuccess(List<String> response) {
        billView.renderAccountNumbers(response);
      }
    }).fire();
  }

  public void addBill(String billName, String provider, String subscription, String account) {

    requestFactory.accountRequest().addBill(billName, provider, subscription, account).fire(new Receiver<BillProxy>() {

      @Override
      public void onSuccess(BillProxy billProxy) {
        billView.clear();
        billView.renderBill(billProxy);
        eventBus.fireEvent(new ApplicationMessage("Successfully added bill payment"));
      }
    });
  }
}
