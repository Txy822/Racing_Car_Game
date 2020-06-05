package Server.Response;

import java.io.Serializable;

public class CreateGameResponse implements Response, Serializable {

  private int code;
  private int gameId;

  public CreateGameResponse(int code, int gameId) {
    this.code = code;
    this.gameId = gameId;
  }

  @Override
  public int getCode() {
    return code;
  }

  public int getGameId() {
    return gameId;
  }
}
