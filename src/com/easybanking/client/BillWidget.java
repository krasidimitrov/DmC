package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class BillWidget extends Composite {

  interface BillWidgetUiBinder extends UiBinder<Widget, BillWidget> {
  }

  private static BillWidgetUiBinder binder = GWT.create(BillWidgetUiBinder.class);

  @UiField
  Label billName;

  @UiField
  Label provider;

  @UiField
  Label subscription;

  @UiField
  Label yourAccountNumber;

  @UiField
  Button deleteButton;

  private BillWidgetPresenter presenter;

  private Long billId;

  public BillWidget() {
    initWidget(binder.createAndBindUi(this));
  }

  public void setPresenter(BillWidgetPresenter presenter) {
    this.presenter = presenter;
  }

  public BillWidgetPresenter getPresenter() {
    return presenter;
  }

  @UiHandler("deleteButton")
  public void onDeleteButtonClick(ClickEvent event) {
    if (Window.confirm("Are you sure you want to delete selected payment?")) {
      presenter.deleteBill(billId);
    }
  }

  public void renderBill(BillProxy billProxy) {
    billId = billProxy.getId();
    billName.setText(billProxy.getBillName());
    provider.setText(billProxy.getProvider());
    subscription.setText(billProxy.getContractNumber());
    yourAccountNumber.setText(billProxy.getYourAccountNumber());
  }

  public void remove() {
    this.removeFromParent();
  }
}