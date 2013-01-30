package com.easybanking.server;

import com.google.inject.ImplementedBy;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
@ImplementedBy(PersistenceUserBase.class)
public interface UserBase {

  public User getCurrentUser(String username);

  User loginViaSession();
}
