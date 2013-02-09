package com.easybanking.client;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidgetPresenter {
  private final BillWidget billWidget;
  private final BankRequestFactory requestFactory;


  public BillWidgetPresenter(BillWidget billWidget, BankRequestFactory requestFactory) {
    this.billWidget = billWidget;
    this.requestFactory = requestFactory;
    this.billWidget.setPresenter(this);
  }
}
