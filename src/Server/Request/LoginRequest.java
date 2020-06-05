package Server.Request;

import Database.DatabaseControl;
import Server.Response.LoginResponse;
import Server.Response.Response;
import java.io.Serializable;

public class LoginRequest implements Request, DatabaseRequest, Serializable {

  private String username;
  private String password;
  private DatabaseControl databaseControl;

  public LoginRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // TODO Finish implementation
  @Override
  public Response handleRequest() {
    databaseControl.createConnection();

    return new LoginResponse(1, databaseControl.isRegistered(1, username, password));
  }

  @Override
  public void setDatabase(DatabaseControl databaseControl) {
    this.databaseControl = databaseControl;
  }
}
