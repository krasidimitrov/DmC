package com.easybanking.server;

import com.google.appengine.api.datastore.Query;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.code.twig.ObjectDatastore;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class PersistenceUserBase implements UserBase {

  private final Provider<ObjectDatastore> datastore;

  @Inject
  public PersistenceUserBase(Provider<ObjectDatastore> datastore){
    this.datastore = datastore;

    saveDefaultUser(datastore);
  }

  public User getCurrentUser(String username) {
    return datastore.get().find().type(User.class).addFilter("username", Query.FilterOperator.EQUAL, username).returnUnique().now();
  }

  public User loginViaSession() {

    User user = null;

    HttpServletRequest httpServletRequest = RequestFactoryServlet.getThreadLocalRequest();
    HttpSession session = httpServletRequest.getSession();
    Object userObj = session.getAttribute("user");
    if (userObj != null && userObj instanceof User) {
      user = (User) userObj;
    }

    return user;
  }

  public User login(String username, String password) {

    User loadedUser = datastore.get().find().type(User.class).addFilter("username", Query.FilterOperator.EQUAL, username).returnUnique().now();

    if (loadedUser != null) {

      if (loadedUser.getPassword().equals(password)) {
        loadedUser.setLoggedIn(true);
        storeUserInSession(loadedUser);
      } else {
        loadedUser.setLoggedIn(false);
      }

      return loadedUser;

    } else {
      return null;
    }
  }

  private void storeUserInSession(User user) {
    HttpServletRequest request = RequestFactoryServlet.getThreadLocalRequest();
    HttpSession session = request.getSession();
    session.setAttribute("user", user);
  }

  public void logout() {
    HttpServletRequest httpServletRequest = RequestFactoryServlet.getThreadLocalRequest();
    HttpSession session = httpServletRequest.getSession();
    session.removeAttribute("user");
  }

  private void saveDefaultUser(Provider<ObjectDatastore> datastore) {

    List<User> users = datastore.get().find().type(User.class).addFilter("username", Query.FilterOperator.EQUAL, "krasi").returnAll().now();

    if (users.size() == 0) {
      User user = new User();
      user.setUsername("krasi");
      user.setId(1l);
      user.setEmail("krasi@gmail.com");
      user.setPassword("1234");

      User user2 = new User();
      user2.setId(2l);
      user2.setUsername("eli");
      user2.setEmail("eli_eli@gmail.com");
      user2.setPassword("1234");

      Account account = new Account(1l, "BG64BUIN95611000127222", 350, "usd", 2.5, 6);
      account.setId(500l);
      account.setCardNumbers(Lists.newArrayList("67606995000048023", "4138890010964725"));
      Account account2 = new Account(1l, "BG64BUIN95611000127333", 500, "eur", 2, 3);
      account2.setId(501l);
      Account account3 = new Account(1l, "BG64BUIN95611000127444", 200, "bgn", 4, 12);
      account3.setId(502l);
      Account account4 = new Account(2l, "BG64BUIN95611000127555", 200, "bgn", 4, 12);
      account4.setId(503l);
      Transaction transaction = new Transaction(1l, "BG64BUIN95611000127222", "BG64BUIN95611000127555", 10, 200, "usd");
      transaction.setInOrOut(0);
      transaction.setId(1000l);
      Transaction transaction2 = new Transaction(1l, "BG64BUIN95611000127222", "BG64BUIN95611000127555", 10, 200, "usd");
      transaction.setId(1001l);
      transaction2.setInOrOut(1);
      datastore.get().store(account);
      datastore.get().store(account2);
      datastore.get().store(account3);
      datastore.get().store(account4);
      datastore.get().store(user);
      datastore.get().store(user2);
      datastore.get().store(transaction);
      datastore.get().store(transaction2);
    }
  }
}
