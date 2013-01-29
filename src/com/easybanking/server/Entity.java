package com.easybanking.server;

/**
 * @author Krasimir Dimitrov (krasimir.dimitrov@clouway.com, kpackapgo@gmail.com)
 */
public interface Entity {
  /**
   * Gets object id.
   *
   * @return object id.
   */
  Object getId();

  /**
   * Get version.
   *
   * @return version number.
   */
  Integer getVersion();
}