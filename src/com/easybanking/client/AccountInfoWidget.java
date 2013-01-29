package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountInfoWidget {
  interface AccountInfoWidgetUiBinder extends UiBinder<HTMLPanel, AccountInfoWidget> {
  }

  private static AccountInfoWidgetUiBinder ourUiBinder = GWT.create(AccountInfoWidgetUiBinder.class);

  public AccountInfoWidget() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

  }
}