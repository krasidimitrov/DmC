package com.easybanking.client;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountViewImpl extends Composite implements AccountView{
  private AccountPresenter presenter;

  @Override
  public void setPresenter(AccountPresenter presenter) {

    this.presenter = presenter;
  }

  interface AccountViewImplUiBinder extends UiBinder<HTMLPanel, AccountViewImpl> {
  }

  private static AccountViewImplUiBinder ourUiBinder = GWT.create(AccountViewImplUiBinder.class);

  public interface Style extends CssResource {

    String clearBoth();

    String accountListStyle();

    String accountNumberStyle();

    String topPanel();

    String accountInfoLabelStyle();

    String cardNumberLabel();
  }

  @UiField
  HTMLPanel accountList;
  @UiField
  Style style;

  @UiField
  TextBox balance;
  @UiField
  TextBox interestRate;
  @UiField
  TextBox interestInterval;
  @UiField
  HTMLPanel cardNumbers;

  public AccountViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  @Override
  public void loadAccounts(List<String> accounts) {
    accountList.clear();
    for(String account : accounts){
      final Label acc = new Label(account);
      acc.setStyleName(style.accountNumberStyle());
      acc.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          presenter.fillAccountInfo(acc.getText());
        }
      });
      accountList.add(acc);
    }
  }

  @Override
  public void renderAccountInfo(AccountProxy response) {
    if(response.getCurrency().equals("bgn"))
    balance.setText(response.getBalance() +"lv");
    if(response.getCurrency().equals("usd"))
    balance.setText(response.getBalance()+"$");
    if(response.getCurrency().equals("eur"))
    balance.setText(response.getBalance()+"€");

    interestRate.setText(response.getInterest()+"%");

    interestInterval.setText(response.getInterestInterval()+ " months");


    cardNumbers.clear();
    for (String cardNumber : response.getCardNumbers()) {
      Label label = new Label(cardNumber);
      label.addStyleName(style.cardNumberLabel());
      cardNumbers.add(label);
    }
  }

}