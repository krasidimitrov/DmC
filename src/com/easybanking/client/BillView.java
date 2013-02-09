package com.easybanking.client;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface BillView {
  void setPresenter(BillPresenter presenter);

  void renderBills(List<BillProxy> response);

  void renderAccountNumbers(List<String> accountNumbers);

  void renderInitialData();

  void renderBill(BillProxy billProxy);

  void clear();
}
