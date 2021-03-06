package com.easybanking.client;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface AccountView {
  void setPresenter(AccountPresenter presenter);

  void loadAccounts(List<String> accounts);

  void renderAccountInfo(AccountProxy response);
}
