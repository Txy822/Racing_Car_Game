package Server.Response;

import Server.Game;
import java.io.Serializable;
import java.util.ArrayList;

public class GetGamesResponse implements Response, Serializable {

  private int code;
  private ArrayList<Game> games;

  public GetGamesResponse(int code, ArrayList<Game> games) {
    this.code = code;
    this.games = games;
  }

  @Override
  public int getCode() {
    return code;
  }

  public ArrayList<Game> getGames() {
    return games;
  }
}
