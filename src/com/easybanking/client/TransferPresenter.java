package com.easybanking.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class TransferPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private TransferView transferView;

  public TransferPresenter(BankRequestFactory requestFactory, TransferView transferView) {
    this.requestFactory = requestFactory;
    this.transferView = transferView;
    this.transferView.setPresenter(this);

  }
}
