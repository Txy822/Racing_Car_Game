package Server.Request;

import Server.MultiplayerGameManager;
import Server.Player;
import Server.Response.GetPlayerPositionsResponse;
import Server.Response.Response;
import java.io.Serializable;
import java.util.ArrayList;

public class GetPlayerPositionsRequest implements Request, MultiplayerRequest, Serializable {

  private int gameId;
  private MultiplayerGameManager multiplayerGameManager;

  public GetPlayerPositionsRequest(int gameId) {
    this.gameId = gameId;
  }

  // TODO Finish implementation
  @Override
  public Response handleRequest() {
    ArrayList<Player> players = multiplayerGameManager.getPlayerPositions(gameId);

    return new GetPlayerPositionsResponse(1, players);
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }
}
