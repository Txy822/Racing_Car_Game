package Server.Response;

import Server.Player;
import java.io.Serializable;
import java.util.ArrayList;

public class GetPlayerPositionsResponse implements Response, Serializable {

  private int code;
  private ArrayList<Player> players;

  public GetPlayerPositionsResponse(int code, ArrayList<Player> players) {
    this.code = code;
    this.players = players;
  }

  @Override
  public int getCode() {
    return code;
  }

  public ArrayList<Player> getPositions() {
    return players;
  }
}
