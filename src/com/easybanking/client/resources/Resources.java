package com.easybanking.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public interface Resources extends ClientBundle {

  public static final Resources INSTANCE = GWT.create(Resources.class);

  @Source(value = "bgn.png")
  ImageResource bgn();

  @Source(value = "eur.png")
  ImageResource eur();

  @Source(value = "usd.png")
  ImageResource usd();

  @Source(value = "grey.jpg")
  ImageResource grey();
}