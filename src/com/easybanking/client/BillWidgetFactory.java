package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidgetFactory {

  BankRequestFactory requestFactory = GWT.create(BankRequestFactory.class);

  public BillWidget createBillWidget(BillProxy billProxy){

    requestFactory.initialize(new SimpleEventBus());

    BillWidget billWidget = new BillWidget();
    BillWidgetPresenter presenter = new BillWidgetPresenter(billWidget, requestFactory);
    billWidget.renderBill(billProxy);
    return billWidget;
  }

}
