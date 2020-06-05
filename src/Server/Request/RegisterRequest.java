package Server.Request;

import Database.DatabaseControl;
import Server.Response.DefaultResponse;
import Server.Response.Response;
import java.io.Serializable;

public class RegisterRequest implements Request, DatabaseRequest, Serializable {

  private String username;
  private String password;
  private String email;
  private DatabaseControl databaseControl;

  public RegisterRequest(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  @Override
  public Response handleRequest() {
    databaseControl.createConnection();

    databaseControl.insertPlayerDetail(username, email, password);

    return new DefaultResponse(1);
  }

  @Override
  public void setDatabase(DatabaseControl databaseControl) {
    this.databaseControl = databaseControl;
  }
}
