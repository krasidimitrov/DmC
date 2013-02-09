package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class TransactionWidget extends Composite {

  interface TransactionWidgetUiBinder extends UiBinder<HTMLPanel, TransactionWidget> {
  }

  private static TransactionWidgetUiBinder ourUiBinder = GWT.create(TransactionWidgetUiBinder.class);

  @UiField
  Label transactionId;
  @UiField
  Label yourAccountNumber;
  @UiField
  Label theirAccountNumber;
  @UiField
  Label amount;
  @UiField
  Label balanceAfterTransaction;
  @UiField
  Label currency;

  public TransactionWidget(TransactionProxy transactionProxy) {

    initWidget(ourUiBinder.createAndBindUi(this));
    transactionId.setText(String.valueOf(transactionProxy.getId()));
    yourAccountNumber.setText(transactionProxy.getYourAccountNumber());
    theirAccountNumber.setText(transactionProxy.getTheirAccountNumber());
    amount.setText(String.valueOf(transactionProxy.getAmount()));
    balanceAfterTransaction.setText(String.valueOf(transactionProxy.getBalanceAfterTransaction()));
    currency.setText(transactionProxy.getCurrency());
  }
}