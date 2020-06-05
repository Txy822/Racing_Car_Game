package Server.Request;

import Server.Response.Response;
import java.io.Serializable;

public class CloseConnectionRequest implements Request, Serializable {

  @Override
  public Response handleRequest() {
    return null;
  }
}
