package com.easybanking.client;

import com.google.gwt.core.client.GWT;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidgetFactory {

  BankRequestFactory requestFactory = GWT.create(BankRequestFactory.class);

  public BillWidget createBillWidget(BillProxy billProxy){
    BillWidget billWidget = new BillWidget();
    BillWidgetPresenter presenter = new BillWidgetPresenter(billWidget, requestFactory);
    billWidget.renderBill(billProxy);
    return billWidget;
  }

}
