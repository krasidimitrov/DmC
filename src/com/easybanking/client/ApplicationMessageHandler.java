package com.easybanking.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Ivan Lazov <ivan.lazov@clouway.com>
 */
public interface ApplicationMessageHandler extends EventHandler {
  void onApplicationMessage(ApplicationMessage event);
}
