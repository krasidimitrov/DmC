package com.easybanking.client;

import com.google.web.bindery.requestfactory.shared.Receiver;

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

  public void deleteBill(Long billId) {

    requestFactory.accountRequest().deleteBill(billId).fire(new Receiver<Void>() {

      @Override
      public void onSuccess(Void response) {
        billWidget.remove();
      }
    });
  }
}
