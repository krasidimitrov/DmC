package com.easybanking.client;

import com.easybanking.client.login.Login;
import com.easybanking.client.login.LoginView;
import com.easybanking.client.login.LoginViewImpl;
import com.gargoylesoftware.htmlunit.javascript.host.Event;
import com.github.gwtbootstrap.client.ui.Nav;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class MainViewImpl  extends Composite{
  private AccountView accountView;
  private BillView billView;
  private CalculatorView calculatorView;
  private TransferView transferView;
  private UserProvider userProvider;

  interface MainViewImplUiBinder extends UiBinder<HTMLPanel, MainViewImpl> {
  }

  private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

  private Widget activeEditor;
  private BankRequestFactory requestFactory;

  @UiField
  NavLink accountTabButton;
  //@UiField
  //Label transferTabButton;
  //@UiField
  //Label creditTabButton;
  //@UiField
  //Label calculatorTabButton;
  @UiField
  HTMLPanel mainPanel;

  @UiField
  NavLink logoutButton;

  @UiField
  HTMLPanel viewPanel;

  @Inject
  public MainViewImpl(final BankRequestFactory requestFactory, final UserProvider userProvider, final AccountView accountView, final TransferView transferView, final BillView billView, final CalculatorView calculatorView) {
    this.userProvider = userProvider;

    initWidget(ourUiBinder.createAndBindUi(this));

    this.accountView = accountView;
    this.billView = billView;
    this.calculatorView = calculatorView;
    this.transferView = transferView;
    this.requestFactory = requestFactory;
    new TransferPresenter(requestFactory , this.transferView);
    activeEditor = (Widget) transferView;
    viewPanel.add(activeEditor);

    accountTabButton.addHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        changeEditor((Widget) accountView, new AccountPresenter(requestFactory, accountView, userProvider));
      }
    }, ClickEvent.getType());
  }

  @UiHandler("logoutButton")
  public void onLogoutButtonClick(ClickEvent event) {

    RootPanel.get().clear();

    LoginView loginView = new LoginViewImpl();
    Login login = new Login(loginView, requestFactory);
    loginView.setPresenter(login);
    loginView.setMainView(new MainViewImpl(requestFactory, userProvider, accountView, transferView, billView, calculatorView));

    RootPanel.get().add((Widget) loginView);
  }

  @UiHandler("accountTabButton")
  public void onAccountTabButtonClick(ClickEvent event) {
    changeEditor((Widget) accountView, new AccountPresenter(requestFactory, accountView, userProvider));
  }

  //@UiHandler("accountTabButton")
  //public void onAccountButtonClicked(ClickEvent event){
  //
  //  changeEditor((Widget) accountView, new AccountPresenter(requestFactory, accountView, userProvider));
  //}

  //@UiHandler("transferTabButton")
  //public void onTransferButtonClicked(ClickEvent event){
  //  changeEditor((Widget) transferView, new TransferPresenter(requestFactory, transferView));
  //}

  //@UiHandler("creditTabButton")
  //public void onCreditButtonClicked(ClickEvent event){
  //  changeEditor((Widget) billView, new BillPresenter(requestFactory, billView));
  //}

  //@UiHandler("calculatorTabButton")
  //public void onCalculatorButtonClicked(ClickEvent event){
  //  changeEditor((Widget) calculatorView, new CalculatorPresenter(requestFactory, calculatorView));
  //}

  private void changeEditor(Widget newActiveEditor, Presenter presenter) {
    viewPanel.remove(activeEditor);
    activeEditor = newActiveEditor;
    viewPanel.add(activeEditor);
  }
}