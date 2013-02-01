package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

import java.awt.peer.LabelPeer;
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


  @UiField
  HTMLPanel accountList;

  public AccountViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }

  @Override
  public void loadAccounts(List<String> accounts) {
    accountList.clear();
    for(String account : accounts){
      Label acc = new Label(account);
      accountList.add(acc);
    }
  }

}