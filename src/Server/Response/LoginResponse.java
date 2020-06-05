package Server.Response;

import java.io.Serializable;

public class LoginResponse implements Response, Serializable {

  private int code;
  private boolean successful;

  public LoginResponse(int code, boolean successful) {
    this.code = code;
    this.successful = successful;
  }

  public int getCode() {
    return code;
  }

  public boolean isSuccessful() {
    return successful;
  }
}
