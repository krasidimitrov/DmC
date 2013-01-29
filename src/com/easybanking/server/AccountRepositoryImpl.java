package com.easybanking.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.google.code.twig.ObjectDatastore;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountRepositoryImpl implements AccountRepository {


  private final Provider<ObjectDatastore> datastore;

  @Inject
  public AccountRepositoryImpl(Provider<ObjectDatastore> datastore) {
    this.datastore = datastore;
  }

  @Override
  public void createTestData() {

  }

  @Override
  public List<String> getAccountNumbersByUser(String user) {
//    Query query = new Query("Account");
//    query.setAncestor(KeyFactory.createKey("User",user));
//    query.addProjection(new PropertyProjection("number", String.class));
//
//    PreparedQuery preparedQuery = datastoreService.prepare(query);
//    return convertToStringList(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
    return Lists.newArrayList("abc123bg","bbb321gg");
  }

  @Override
  public Account getAccount(String username, String number) {
//    Query query = new Query("Account");
////    query.setAncestor(KeyFactory.createKey("User", username));
//    query.setFilter(new Query.FilterPredicate("number", Query.FilterOperator.EQUAL,number));
//    PreparedQuery preparedQuery = datastoreService.prepare(query);
//    return convertToAccountObject(preparedQuery.asSingleEntity());
     return new Account("abc123bg", 12, "bgn", 3);
  }

  private List<String> convertToStringList(List<Entity> listOfEntities) {
    List<String> listOfStrings = new ArrayList<String>();
    for (Entity entity : listOfEntities) {
      listOfStrings.add((String) entity.getProperty("number"));
    }
    return listOfStrings;
  }

  private Account convertToAccountObject(Entity entity){
    String number = String.valueOf(entity.getProperty("number"));
    double balance = Double.parseDouble(String.valueOf(entity.getProperty("balance")));
    String currency = String.valueOf(entity.getProperty("currency"));
    double interest = Double.parseDouble(String.valueOf(entity.getProperty("interest")));
    return new Account(number,balance,currency,interest);
  }
}
