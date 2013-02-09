package com.easybanking.client;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class BillViewImpl extends Composite implements BillView {
  private BillPresenter presenter;

  @Override
  public void setPresenter(BillPresenter presenter) {

    this.presenter = presenter;
  }

  @UiField
  HTMLPanel billPanel;
  @UiField
  ListBox accountBox;

  @UiField
  ListBox billNames;

  @UiField
  ListBox providers;

  @UiField
  Button addBillButton;

  @UiField
  TextBox subscription;

  @Override
  public void renderBills(List<BillProxy> response) {
    billPanel.clear();

    for(BillProxy bill: response){
      BillWidget billWidget = factory.createBillWidget(bill);
      billPanel.add(billWidget);
    }
  }

  @Override
  public void renderBill(BillProxy billProxy) {
    billPanel.add(factory.createBillWidget(billProxy));
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

  @Override
  public void clear() {
    billNames.setSelectedIndex(0);
    providers.clear();
    subscription.setText("");
    accountBox.setSelectedIndex(0);
  }

  interface BillViewImplUiBinder extends UiBinder<HTMLPanel, BillViewImpl> {
  }

  private static BillViewImplUiBinder ourUiBinder = GWT.create(BillViewImplUiBinder.class);

  private BillWidgetFactory factory;

  @Inject
  public BillViewImpl(BillWidgetFactory factory) {
    this.factory = factory;
    initWidget(ourUiBinder.createAndBindUi(this));

    renderBillNames();
  }

  @UiHandler("billNames")
  public void onBillNamesChange(ChangeEvent event) {

    String billName = billNames.getValue(billNames.getSelectedIndex());

    renderProviderNames(billName);
  }

  @UiHandler("addBillButton")
  public void onAddBillButtonClick(ClickEvent event) {
    String billName = billNames.getValue(billNames.getSelectedIndex());
    String provider = providers.getValue(providers.getSelectedIndex());
    String account = accountBox.getValue(accountBox.getSelectedIndex());

    presenter.addBill(billName, provider, subscription.getText(), account);
  }

  private void renderProviderNames(String billName) {

    providers.clear();

    if (billName.equals("")) {
      return;
    }

    if (billName.equals("Cable/Satellite TV")) {
      providers.addItem("Evrokom Tsarevets");
      providers.addItem("Vivacom");
      providers.addItem("Bulsatcom");
    }

    if (billName.equals("Electricity")) {
      providers.addItem("CEZ Electro Bulgaria - Sofia");
      providers.addItem("CEZ Electro Bulgaria - Pleven");
      providers.addItem("E.ON Bulgaria");
      providers.addItem("EVN Bulgaria - Plovdiv");
      providers.addItem("EVN Bulgaria - Stara Zagora");
    }

    if (billName.equals("Internet")) {
      providers.addItem("Cooolbox");
      providers.addItem("Max Telecom");
      providers.addItem("Megalan Network");
      providers.addItem("Max Telecom");
    }

    if (billName.equals("Mobile Phone")) {
      providers.addItem("M-tel");
      providers.addItem("Globul");
      providers.addItem("Vivacom");
    }
  }

  private void renderBillNames() {
    billNames.addItem("");
    billNames.addItem("Cable/Satellite TV");
    billNames.addItem("Electricity");
    billNames.addItem("Internet");
    billNames.addItem("Mobile Phone");
  }
}