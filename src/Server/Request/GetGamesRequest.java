package Server.Request;

import Server.Game;
import Server.MultiplayerGameManager;
import Server.Response.GetGamesResponse;
import Server.Response.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class GetGamesRequest implements Request, MultiplayerRequest, Serializable {

  private MultiplayerGameManager multiplayerGameManager;

  @Override
  public Response handleRequest() {
    Map<Integer, Game> games = multiplayerGameManager.getGames();

    return new GetGamesResponse(1, new ArrayList<>(games.values()));
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }
}
