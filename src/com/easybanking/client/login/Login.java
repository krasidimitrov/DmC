package com.easybanking.client.login;

import com.easybanking.client.BankRequestFactory;
import com.easybanking.client.UserProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class Login implements LoginView.Presenter {

  private final LoginView view;
  private final BankRequestFactory requestFactory;

  public Login(LoginView view, BankRequestFactory requestFactory) {
    this.view = view;
    this.requestFactory = requestFactory;
  }

  public void login(String username, String password) {

    if (username.equals("") || password.equals("")) {
      view.showErrorMessage("Username/password cannot be empty!");
      return;
    } else {
      view.clearErrorMessage();
    }

    requestFactory.getLoginRequest().login(username, password).fire(new Receiver<UserProxy>() {

      @Override
      public void onSuccess(UserProxy user) {

        if (user == null || !user.isLoggedIn()) {
          view.showErrorMessage("Wrong username/password. Try again.");
          return;
        }

        view.showMainView();
      }
    });
  }
}
