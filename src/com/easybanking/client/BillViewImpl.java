package com.easybanking.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class BillViewImpl extends Composite implements BillView {
  private BillPresenter presenter;

  @Override
  public void setPresenter(BillPresenter presenter) {

    this.presenter = presenter;
  }

  interface BillViewImplUiBinder extends UiBinder<HTMLPanel, BillViewImpl> {
  }

  private static BillViewImplUiBinder ourUiBinder = GWT.create(BillViewImplUiBinder.class);

  public BillViewImpl() {
    initWidget(ourUiBinder.createAndBindUi(this));
  }
}