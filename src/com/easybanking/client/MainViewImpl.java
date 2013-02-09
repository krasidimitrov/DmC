package com.easybanking.client;

import com.easybanking.client.login.Login;
import com.easybanking.client.login.LoginView;
import com.easybanking.client.login.LoginViewImpl;
import com.github.gwtbootstrap.client.ui.AlertBlock;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

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

  public interface MainViewCss extends CssResource {
    String hidden();
  }

  private static MainViewImplUiBinder ourUiBinder = GWT.create(MainViewImplUiBinder.class);

  private Widget activeEditor;
  private BankRequestFactory requestFactory;

  @UiField
  NavLink accountTabButton;
  @UiField
  NavLink transferTabButton;
  @UiField
  NavLink creditTabButton;
  @UiField
  NavLink calculatorTabButton;
  @UiField
  HTMLPanel mainPanel;

  @UiField
  NavLink logoutButton;

  @UiField
  HTMLPanel viewPanel;

  @UiField
  AlertBlock messagePanel;

  @UiField
  MainViewCss style;

  private final EventBus eventBus;

  @Inject
  public MainViewImpl(final BankRequestFactory requestFactory, final UserProvider userProvider, final AccountView accountView, final TransferView transferView, final BillView billView,
                      final CalculatorView calculatorView, final EventBus eventBus) {
    this.userProvider = userProvider;
    this.eventBus = eventBus;

    eventBus.addHandler(ApplicationMessage.TYPE, new ApplicationMessageHandler() {
      @Override
      public void onApplicationMessage(ApplicationMessage event) {
        messagePanel.setText(event.getMessage());
        messagePanel.removeStyleName(style.hidden());

        Timer timer = new Timer() {
          @Override
          public void run() {
            messagePanel.clear();
            messagePanel.addStyleName(style.hidden());
          }
        };

        timer.schedule(3000);
      }
    });

    initWidget(ourUiBinder.createAndBindUi(this));

    this.accountView = accountView;
    this.billView = billView;
    this.calculatorView = calculatorView;
    this.transferView = transferView;
    this.requestFactory = requestFactory;
    new TransferPresenter(requestFactory , this.transferView, eventBus);
    activeEditor = (Widget) transferView;
    viewPanel.add(activeEditor);

    messagePanel.addStyleName(style.hidden());
  }

  @UiHandler("logoutButton")
  public void onLogoutButtonClick(ClickEvent event) {

    RootPanel.get().clear();

    LoginView loginView = new LoginViewImpl();
    Login login = new Login(loginView, requestFactory);
    loginView.setPresenter(login);
    loginView.setMainView(new MainViewImpl(requestFactory, userProvider, accountView, transferView, billView, calculatorView, eventBus));

    RootPanel.get().add((Widget) loginView);
  }

  @UiHandler("accountTabButton")
  public void onAccountTabButtonClick(ClickEvent event) {
    changeEditor((Widget) accountView, new AccountPresenter(requestFactory, accountView, userProvider));
  }

  @UiHandler("transferTabButton")
  public void onTransferButtonClicked(ClickEvent event){
    changeEditor((Widget) transferView, new TransferPresenter(requestFactory, transferView, eventBus));
  }

  @UiHandler("creditTabButton")
  public void onCreditButtonClicked(ClickEvent event){
    changeEditor((Widget) billView, new BillPresenter(requestFactory, billView, eventBus));
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
}