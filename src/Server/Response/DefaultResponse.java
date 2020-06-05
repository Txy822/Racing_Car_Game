package Server.Response;

import java.io.Serializable;

public class DefaultResponse implements Response, Serializable {

  private int code;

  public DefaultResponse(int code) {
    this.code = code;
  }

  @Override
  public int getCode() {
    return code;
  }
}
