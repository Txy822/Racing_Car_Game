package Server.Response;

import Server.Game;
import java.io.Serializable;

public class GameResponse implements Response, Serializable {

  private Game game;

  public GameResponse(Game game) {
    this.game = game;
  }

  @Override
  public int getCode() {
    return 0;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }
}
