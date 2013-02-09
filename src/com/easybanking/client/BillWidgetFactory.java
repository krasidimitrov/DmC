package com.easybanking.client;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidgetFactory {

  private final BankRequestFactory requestFactory;
  private final EventBus eventBus;

  @Inject
  public BillWidgetFactory(BankRequestFactory requestFactory, EventBus eventBus) {
    this.requestFactory = requestFactory;
    this.eventBus = eventBus;
  }

  public BillWidget createBillWidget(BillProxy billProxy){

    BillWidget billWidget = new BillWidget();
    BillWidgetPresenter presenter = new BillWidgetPresenter(billWidget, requestFactory, eventBus);
    billWidget.renderBill(billProxy);
    return billWidget;
  }

}
