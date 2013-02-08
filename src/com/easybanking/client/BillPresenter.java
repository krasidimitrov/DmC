package com.easybanking.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class BillPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private BillView billView;

  public BillPresenter(BankRequestFactory requestFactory, BillView billView) {
    this.requestFactory = requestFactory;
    this.billView = billView;
    this.billView.setPresenter(this);
  }
}
