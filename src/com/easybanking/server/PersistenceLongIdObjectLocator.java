package com.easybanking.server;

import com.google.code.twig.ObjectDatastore;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.shared.Locator;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public class PersistenceLongIdObjectLocator extends Locator<Entity, Long> {

  private Provider<ObjectDatastore> datastore;

  @Inject
  public PersistenceLongIdObjectLocator(Provider<ObjectDatastore> datastore) {
    this.datastore = datastore;
  }

  @Override
  public Entity create(Class<? extends Entity> clazz) {
    try {
      return clazz.newInstance();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Entity find(Class<? extends Entity> clazz, Long id) {
    return datastore.get().load(clazz, id);
  }

  @Override
  public Class<Entity> getDomainType() {
    return Entity.class;
  }

  @Override
  public Long getId(Entity domainObject) {
    return (Long) domainObject.getId();
  }

  @Override
  public Class<Long> getIdType() {
    return Long.class;
  }

  @Override
  public Integer getVersion(Entity domainObject) {
    return domainObject.getVersion();
  }
}
