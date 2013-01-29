package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class CreditViewImpl extends Composite implements CreditView {
  private CreditPresenter presenter;

  @Override
  public void setPresenter(CreditPresenter presenter) {

    this.presenter = presenter;
  }

  interface CreditViewImplUiBinder extends UiBinder<HTMLPanel, CreditViewImpl> {
  }

  private static CreditViewImplUiBinder ourUiBinder = GWT.create(CreditViewImplUiBinder.class);

  public CreditViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}