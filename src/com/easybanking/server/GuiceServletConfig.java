package com.easybanking.server;


import com.easybanking.inject.MyConstraintValidatorFactory;
import com.easybanking.inject.MyRequestFactoryServlet;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.code.twig.ObjectDatastore;
import com.google.code.twig.annotation.AnnotationObjectDatastore;
import com.google.gwt.user.client.Cookies;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.ServletModule;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new ServletModule() {
      @Override
      protected void configureServlets() {


        serve("/gwtRequest").with(MyRequestFactoryServlet.class);

      }

      @Provides
      @Singleton
      ValidatorFactory getValidatorFactory(Injector injector) {
        return Validation.byDefaultProvider().configure().constraintValidatorFactory(new MyConstraintValidatorFactory(injector)).buildValidatorFactory();
      }

      @Provides
      @Singleton
      Validator getValidator(ValidatorFactory validatorFactory) {
        return validatorFactory.getValidator();
      }

      @Provides
      @RequestScoped
      ObjectDatastore getObjectDatastore(){
        return new AnnotationObjectDatastore();
      }

      @Provides
      @RequestScoped
      User getCurrentUser(UserBase userBase){
        return userBase.loginViaSession();
      }
    });
  }
}
