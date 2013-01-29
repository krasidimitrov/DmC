package com.easybanking.client;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class CalculatorPresenter implements Presenter {
  private final BankRequestFactory requestFactory;
  private CalculatorView calculatorView;

  public CalculatorPresenter(BankRequestFactory requestFactory, CalculatorView calculatorView) {
    this.requestFactory = requestFactory;
    this.calculatorView = calculatorView;
    this.calculatorView.setPresenter(this);
  }


  public double convertMoney(double amount, double multiplier){
    return amount*multiplier;
  }
}
