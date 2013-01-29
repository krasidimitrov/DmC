package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MainViewImpl  extends Composite{
  private AccountView accountView;
  private CreditView creditView;
  private CalculatorView calculatorView;
  private TransferView transferView;
  private UserProvider userProvider;

  interface MainViewImplUiBinder extends UiBinder<HTMLPanel, MainViewImpl> {
  }

  private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

  private Widget activeEditor;
  private BankRequestFactory requestFactory;

  @UiField
  Button accountTabButton;
  @UiField
  Button transferTabButton;
  @UiField
  Button creditTabButton;
  @UiField
  Button calculatorTabButton;
  @UiField
  HTMLPanel mainPanel;
  @UiField
  HTMLPanel viewPanel;

  @Inject
  public MainViewImpl(BankRequestFactory requestFactory, UserProvider userProvider, AccountView accountView, TransferView transferView, CreditView creditView, CalculatorView calculatorView) {
    this.userProvider = userProvider;

    initWidget(ourUiBinder.createAndBindUi(this));

    this.accountView = accountView;
    this.creditView = creditView;
    this.calculatorView = calculatorView;
    this.transferView = transferView;
    this.requestFactory = requestFactory;
    new TransferPresenter(requestFactory , this.transferView);
    activeEditor = (Widget) transferView;
    viewPanel.add(activeEditor);

    insertTestData();
  }

  @UiHandler("accountTabButton")
  public void onAccountButtonClicked(ClickEvent event){

    changeEditor((Widget) accountView, new AccountPresenter(requestFactory, accountView, userProvider));
  }

  @UiHandler("transferTabButton")
  public void onTransferButtonClicked(ClickEvent event){
    changeEditor((Widget) transferView, new TransferPresenter(requestFactory, transferView));
  }

  @UiHandler("creditTabButton")
  public void onCreditButtonClicked(ClickEvent event){
    changeEditor((Widget) creditView, new CreditPresenter(requestFactory, creditView));
  }

  @UiHandler("calculatorTabButton")
  public void onCalculatorButtonClicked(ClickEvent event){
    changeEditor((Widget) calculatorView, new CalculatorPresenter(requestFactory, calculatorView));
  }


  private void changeEditor(Widget newActiveEditor, Presenter presenter) {
    viewPanel.remove(activeEditor);
    activeEditor = newActiveEditor;
    viewPanel.add(activeEditor);
  }

  public void insertTestData(){

  }
}