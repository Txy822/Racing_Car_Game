package Server.Request;

import Database.DatabaseControl;

public interface DatabaseRequest {

  /**
   * Allows injection of a DatabaseControl instance into the request
   */
  void setDatabase(DatabaseControl databaseControl);
}
