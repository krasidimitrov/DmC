package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class TransferViewImpl extends Composite implements TransferView {
  private TransferPresenter presenter;

  @Override
  public void setPresenter(TransferPresenter presenter) {

    this.presenter = presenter;
  }

  interface TransferViewImplUiBinder extends UiBinder<HTMLPanel, TransferViewImpl> {
  }

  private static TransferViewImplUiBinder ourUiBinder = GWT.create(TransferViewImplUiBinder.class);


  @UiField
  ListBox accountBox;
  @UiField
  Label balanceLabel;
  @UiField
  Label currencyLabel;
  @UiField
  TextBox receiverAccountBox;
  @UiField
  Button transferButton;

  public TransferViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}