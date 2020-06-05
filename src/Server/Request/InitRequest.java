package Server.Request;

import Server.Response.DefaultResponse;
import Server.Response.Response;
import java.io.Serializable;

public class InitRequest implements Request, Serializable {

  @Override
  public Response handleRequest() {
    return new DefaultResponse(1);
  }
}
