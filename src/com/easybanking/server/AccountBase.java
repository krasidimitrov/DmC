package com.easybanking.server;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
@ImplementedBy(PersistenceAccountBase.class)
public interface AccountBase {
  public List<String> loadAccounts();
}
