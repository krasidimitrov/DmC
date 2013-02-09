package com.easybanking.client;

import com.easybanking.client.resources.Resources;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class CalculatorViewImpl extends Composite implements CalculatorView {
  private CalculatorPresenter presenter;

  @Override
  public void setPresenter(CalculatorPresenter presenter) {
    this.presenter = presenter;
  }

  interface CalculatorViewImplUiBinder extends UiBinder<HTMLPanel, CalculatorViewImpl> {
  }

  @UiField
  FlexTable currencyCellTable;
  @UiField
  Button convertButton;
  @UiField
  ListBox currencyList;
  @UiField
  ListBox currencyList2;
  @UiField
  TextBox input;
  @UiField
  TextBox output;


  private static CalculatorViewImplUiBinder ourUiBinder = GWT.create(CalculatorViewImplUiBinder.class);

  //Made that way only for the purpose of showing a visual model
  public CalculatorViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));

    currencyCellTable.setWidget(0,1,new Image(Resources.INSTANCE.bgn()));
    currencyCellTable.setWidget(0,2,new Image(Resources.INSTANCE.usd()));
    currencyCellTable.setWidget(0,3,new Image(Resources.INSTANCE.eur()));
    currencyCellTable.setWidget(1,0,new Image(Resources.INSTANCE.bgn()));
    currencyCellTable.setWidget(2,0,new Image(Resources.INSTANCE.usd()));
    currencyCellTable.setWidget(3,0,new Image(Resources.INSTANCE.eur()));
    currencyCellTable.setWidget(1,1,new Label("-"));
    currencyCellTable.setWidget(1,2,new Label("0.641"));
    currencyCellTable.setWidget(1,3,new Label("0.502"));
    currencyCellTable.setWidget(2,2,new Label("-"));
    currencyCellTable.setWidget(2,1,new Label("1.56"));
    currencyCellTable.setWidget(2,3,new Label("0.79"));
    currencyCellTable.setWidget(3,3,new Label("-"));
    currencyCellTable.setWidget(3,1,new Label("1.96"));
    currencyCellTable.setWidget(3,2,new Label("1.25"));



    currencyCellTable.setCellPadding(5);
//    currencyCellTable.setCellSpacing(10);
    currencyCellTable.setBorderWidth(1);

    currencyList.addItem("bgn","1");
    currencyList.addItem("usd","2");
    currencyList.addItem("eur","3");

    currencyList2.addItem("bgn","1");
    currencyList2.addItem("usd","2");
    currencyList2.addItem("eur","3");
    output.setEnabled(false);

  }

  @UiHandler("convertButton")
  public void onConvertButtonClicked(ClickEvent event){
    String multiplier = ((Label)currencyCellTable.getWidget(Integer.parseInt(currencyList.getValue(currencyList.getSelectedIndex())), Integer.parseInt(currencyList2.getValue(currencyList2.getSelectedIndex())))).getText();
    output.setText(String.valueOf(presenter.convertMoney(Double.parseDouble(input.getText()),Double.parseDouble(multiplier))));
  }
}