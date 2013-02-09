package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class BillViewImpl extends Composite implements BillView {
  private BillPresenter presenter;
  private BillWidgetFactory factory = new BillWidgetFactory();

  @Override
  public void setPresenter(BillPresenter presenter) {

    this.presenter = presenter;
  }

  @UiField
  HTMLPanel billPanel;
  @UiField
  ListBox accountBox;

  @Override
  public void renderBills(List<BillProxy> response) {
    for(BillProxy bill: response){
      BillWidget billWidget = factory.createBillWidget(bill);
      billPanel.add(billWidget);
    }
  }

  @Override
  public void renderAccountNumbers(List<String> accountNumbers) {
    accountBox.clear();
    accountBox.addItem("");
    for(String accNumber : accountNumbers){
      accountBox.addItem(accNumber);
    }
  }

  @Override
  public void renderInitialData() {
    presenter.loadInitialData();
  }

  interface BillViewImplUiBinder extends UiBinder<HTMLPanel, BillViewImpl> {
  }

  private static BillViewImplUiBinder ourUiBinder = GWT.create(BillViewImplUiBinder.class);

  public BillViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}