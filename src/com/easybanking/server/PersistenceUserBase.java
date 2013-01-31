package com.easybanking.server;

import com.google.appengine.api.datastore.Query;
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
      user.setEmail("krasi@gmail.com");
      user.setPassword("1234");

      datastore.get().storeOrUpdate(user);
    }
  }
}
