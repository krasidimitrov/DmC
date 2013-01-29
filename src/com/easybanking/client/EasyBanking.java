package com.easybanking.client;

import com.easybanking.client.gin.SimpleGinInjector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class EasyBanking implements EntryPoint{

  public void onModuleLoad() {
    SimpleGinInjector ginInjector = GWT.create(SimpleGinInjector.class);
    MainViewImpl mainView = ginInjector.getMainViewImpl();
//    MainViewImpl mainView = new MainViewImpl();//addProductView, sellProductView, productView);

    RootPanel.get().add(mainView);
  }
}
