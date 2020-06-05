package Server.Request;

import Server.Response.Response;

public interface Request {

  /**
   * The handling of the request
   *
   * @return A Response object
   */
  Response handleRequest();
}
