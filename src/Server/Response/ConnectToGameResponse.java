package Server.Response;

import java.io.Serializable;

public class ConnectToGameResponse implements Response, Serializable {

  private int code;
  private int playerId;

  public ConnectToGameResponse(int code, int playerId) {
    this.code = code;
    this.playerId = playerId;
  }

  @Override
  public int getCode() {
    return code;
  }

  public int getPlayerId() {
    return playerId;
  }
}
