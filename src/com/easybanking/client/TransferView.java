package com.easybanking.client;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface TransferView {
  void setPresenter(TransferPresenter presenter);

  void renderInitialData(List<String> yourAccountNumbers);

  void renderAccountDetails(AccountProxy response);
}
