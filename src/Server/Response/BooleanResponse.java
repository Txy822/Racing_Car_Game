package Server.Response;

import java.io.Serializable;

public class BooleanResponse implements Response, Serializable {

  private boolean bool;

  public BooleanResponse(boolean bool) {
    this.bool = bool;
  }

  @Override
  public int getCode() {
    return 0;
  }

  public boolean isBool() {
    return bool;
  }

  public void setBool(boolean bool) {
    this.bool = bool;
  }
}
