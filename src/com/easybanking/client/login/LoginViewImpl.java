package com.easybanking.client.login;

import com.easybanking.client.MainViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class LoginViewImpl extends Composite implements LoginView {

  interface LoginViewImplUiBinder extends UiBinder<HTMLPanel, LoginViewImpl> {
  }

  private static LoginViewImplUiBinder uiBinder = GWT.create(LoginViewImplUiBinder.class);

  @UiField
  TextBox username;

  @UiField
  PasswordTextBox password;

  @UiField
  Button loginButton;

  @UiField
  HTMLPanel errorPanel;

  @UiField
  Label errorMessage;

  private Presenter presenter;

  private MainViewImpl mainView;

  public LoginViewImpl() {
    initWidget(uiBinder.createAndBindUi(this));

    username.setMaxLength(15);
    password.setMaxLength(15);
  }

  public void setPresenter(Presenter presenter) {
    this.presenter = presenter;
  }

  @UiHandler("loginButton")
  public void onLoginButtonClick(ClickEvent event) {
    presenter.login(username.getText(), password.getText());
  }

  @Override
  public void showErrorMessage(String message) {

    errorPanel.setVisible(true);
    errorMessage.setText(message);
  }

  @Override
  public void clearErrorMessage() {
    errorPanel.setVisible(false);
  }

  @Override
  public void setMainView(MainViewImpl mainView) {
    this.mainView = mainView;
  }

  @Override
  public void showMainView() {
    RootPanel.get().clear();
    mainView.showLogoutButton();
    RootPanel.get().add(mainView);
  }
}