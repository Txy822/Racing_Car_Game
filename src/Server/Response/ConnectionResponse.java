package Server.Response;

import java.io.Serializable;

public class ConnectionResponse implements Response, Serializable {

  private int code;

  public ConnectionResponse(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return code;
  }
}
