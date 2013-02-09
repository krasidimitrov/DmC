package com.easybanking.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public class ApplicationMessage extends GwtEvent<ApplicationMessageHandler> {
  public static Type<ApplicationMessageHandler> TYPE = new Type<ApplicationMessageHandler>();

  private String message;

  public ApplicationMessage(String message) {
    this.message = message;
  }

  public Type<ApplicationMessageHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ApplicationMessageHandler handler) {
    handler.onApplicationMessage(this);
  }

  public String getMessage() {
    return message;
  }
}
