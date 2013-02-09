package com.easybanking.client;

import com.google.gwt.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidgetPresenter {
  private final BillWidget billWidget;
  private final BankRequestFactory requestFactory;
  private final EventBus eventBus;

  public BillWidgetPresenter(BillWidget billWidget, BankRequestFactory requestFactory, EventBus eventBus) {
    this.billWidget = billWidget;
    this.requestFactory = requestFactory;
    this.billWidget.setPresenter(this);
    this.eventBus = eventBus;
  }

  public void deleteBill(Long billId) {

    requestFactory.accountRequest().deleteBill(billId).fire(new Receiver<Void>() {

      @Override
      public void onSuccess(Void response) {
        billWidget.remove();
        eventBus.fireEvent(new ApplicationMessage("Successfully deleted bill payment."));
      }
    });
  }
}
