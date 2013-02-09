package com.easybanking.client;

import com.easybanking.client.gin.SimpleGinInjector;
import com.easybanking.client.login.LoginView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EasyBanking implements EntryPoint{

  private final SimpleGinInjector ginInjector = GWT.create(SimpleGinInjector.class);

  public void onModuleLoad() {

    setBackgroundColor();

    final MainViewImpl mainView = ginInjector.getMainViewImpl();

    BankRequestFactory requestFactory = ginInjector.getRequestFactory();
    requestFactory.getLoginRequest().loginViaSession().fire(new Receiver<UserProxy>() {

      @Override
      public void onFailure(ServerFailure error) {
        super.onFailure(error);
      }

      @Override
      public void onSuccess(UserProxy user) {

        if (user == null) {
          LoginView loginView = ginInjector.getLoginView();
          loginView.setMainView(mainView);
          RootPanel.get().add((Widget) loginView);
        } else {
          RootPanel.get().add(mainView);
        }
      }
    });
  }

  private void setBackgroundColor() {
    Document.get().getBody().getStyle().setBackgroundColor("lightslategray");
  }
}
