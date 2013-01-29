package com.easybanking.server;

import com.google.appengine.api.datastore.Query;
import com.google.code.twig.ObjectDatastore;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class PersistenceUserBase implements UserBase {

  private final Provider<ObjectDatastore> datastore;

  @Inject
  public PersistenceUserBase(Provider<ObjectDatastore> datastore){
    this.datastore = datastore;
  }

  public User getCurrentUser(String username) {
    return datastore.get().find().type(User.class).addFilter("username", Query.FilterOperator.EQUAL, username).returnUnique().now();
  }
}
