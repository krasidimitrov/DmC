package com.easybanking.server;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.PropertyProjection;
import com.google.appengine.api.datastore.Query;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class AccountRepositoryImpl implements AccountRepository {

  private DatastoreService datastoreService;

  @Inject
  public AccountRepositoryImpl(DatastoreService datastoreService) {
    this.datastoreService = datastoreService;
  }

  @Override
  public void createTestData() {
    Entity testUser = new Entity("User","test@gmail.com");
    testUser.setProperty("password", "12345");
    datastoreService.put(testUser);
    Entity testUser2 = new Entity("User", "test2@gmail.com");
    testUser2.setProperty("password", "12345");
    datastoreService.put(testUser2);
    Entity account = new Entity("Account", KeyFactory.createKey("User", "test@gmail.com"));
    account.setProperty("number", "bg1234512345");
    account.setProperty("balance",300);
    account.setProperty("currency","bgn");
    account.setProperty("interest", 5);
    Entity account2 = new Entity("Account", KeyFactory.createKey("User", "test@gmail.com"));
    account2.setProperty("number", "bg5555555555");
    account2.setProperty("balance",5000);
    account2.setProperty("currency","usd");
    account2.setProperty("interest", 5.5);
    datastoreService.put(account);
    datastoreService.put(account2);

  }

  @Override
  public List<String> getAccountNumbersByUser(String user) {
    Query query = new Query("Account");
    query.setAncestor(KeyFactory.createKey("User",user));
    query.addProjection(new PropertyProjection("number", String.class));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToStringList(preparedQuery.asList(FetchOptions.Builder.withDefaults()));
  }

  @Override
  public Account getAccount(String username, String number) {
    Query query = new Query("Account");
//    query.setAncestor(KeyFactory.createKey("User", username));
    query.setFilter(new Query.FilterPredicate("number", Query.FilterOperator.EQUAL,number));

    PreparedQuery preparedQuery = datastoreService.prepare(query);
    return convertToAccountObject(preparedQuery.asSingleEntity());

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
