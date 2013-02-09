package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidget extends Composite {
  private BillWidgetPresenter presenter;

  public void setPresenter(BillWidgetPresenter presenter) {
    this.presenter = presenter;
  }

  @UiField
  Label personalId;
  @UiField
  Label yourAccountNumber;
  @UiField
  Button deleteButton;
  @UiField
  Label billName;

  public BillWidgetPresenter getPresenter() {
    return presenter;
  }

  public void renderBill(BillProxy billProxy) {
    billName.setText(billProxy.getBillName());
    personalId.setText(billProxy.getContractNumber());
    yourAccountNumber.setText(billProxy.getYourAccountNumber());
  }

  interface BillWidgetUiBinder extends UiBinder<Widget, BillWidget> {
  }

  private static BillWidgetUiBinder binder = GWT.create(BillWidgetUiBinder.class);

  public BillWidget() {

    initWidget(binder.createAndBindUi(this));

  }

}