package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;

import java.util.List;

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
  @UiField
  TextBox amountBox;

  public TransferViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  @Override
  public void renderInitialData(List<String> yourAccountsNumbers) {
    accountBox.clear();
    accountBox.addItem("");
    for(String accountNumber : yourAccountsNumbers){
      accountBox.addItem(accountNumber);
    }
  }

  @Override
  public void renderAccountDetails(AccountProxy response) {
    balanceLabel.setText(response.getBalance()+"");
    if(response.getCurrency().equals("bgn"))
      currencyLabel.setText("lv");
    if(response.getCurrency().equals("usd"))
      currencyLabel.setText("$");
    if(response.getCurrency().equals("eur"))
      currencyLabel.setText("€");
  }

  @Override
  public double getBalanceLabelAmmount() {
    return Double.valueOf(balanceLabel.getText());
  }

  @Override
  public void renderNewlyMadeOutTransaction(TransactionProxy transaction) {
    //TODO: render the newly made transaction
  }

  @Override
  public void setBalanceLabelAmount(String balanceOnYourAccount) {
    balanceLabel.setText(balanceOnYourAccount);
  }

  @UiHandler("accountBox")
  public void onAccountBoxChange(ChangeEvent event){
    presenter.fillAccountData(accountBox.getItemText(accountBox.getSelectedIndex()));
  }

  @UiHandler("transferButton")
  public void onTransfer(ClickEvent event){
    //////////// check if there is number in the amountBox can be done
    presenter.sendMoney(Double.valueOf(amountBox.getText()), accountBox.getItemText(accountBox.getSelectedIndex()), receiverAccountBox.getText());
  }
}