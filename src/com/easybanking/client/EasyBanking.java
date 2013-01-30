package com.easybanking.client;

import com.easybanking.client.gin.SimpleGinInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EasyBanking implements EntryPoint{

  public void onModuleLoad() {

    SimpleGinInjector ginInjector = GWT.create(SimpleGinInjector.class);
    //MainViewImpl mainView = ginInjector.getMainViewImpl();
    //RootPanel.get().add(mainView);

    BankRequestFactory requestFactory = ginInjector.getRequestFactory();

    requestFactory.getLoginRequest().loginViaSession().fire(new Receiver<UserProxy>() {

      @Override
      public void onFailure(ServerFailure error) {
        super.onFailure(error);
      }

      @Override
      public void onSuccess(UserProxy user) {
        Window.alert("Login Request Received");
      }
    });

  }
}
