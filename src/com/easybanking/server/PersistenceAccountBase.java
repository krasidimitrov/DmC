package com.easybanking.server;

import com.google.appengine.api.datastore.Entity;
import com.google.code.twig.ObjectDatastore;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class PersistenceAccountBase implements AccountBase {


  private final Provider<ObjectDatastore> datastore;
  private final Provider<User> currentUser;

  @Inject
  public PersistenceAccountBase(Provider<ObjectDatastore> datastore, Provider<User> currentUser) {
    this.datastore = datastore;
    this.currentUser = currentUser;
  }


}
