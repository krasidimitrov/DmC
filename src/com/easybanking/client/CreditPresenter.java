package com.easybanking.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class CreditPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private CreditView creditView;

  public CreditPresenter(BankRequestFactory requestFactory, CreditView creditView) {
    this.requestFactory = requestFactory;
    this.creditView = creditView;
    this.creditView.setPresenter(this);
  }
}
