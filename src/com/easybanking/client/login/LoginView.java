package com.easybanking.client.login;

import com.easybanking.client.MainViewImpl;

public interface LoginView {

  public interface Presenter {

    void login(String username, String password);
  }

  void setPresenter(Presenter presenter);

  void showErrorMessage(String errorMessage);

  void clearErrorMessage();

  void setMainView(MainViewImpl mainView);

  void showMainView();
}
