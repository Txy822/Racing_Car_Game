package Server.Request;

import Server.MultiplayerGameManager;
import Server.Response.DefaultResponse;
import Server.Response.Response;
import java.io.Serializable;

public class DisconnectGameRequest implements Request, MultiplayerRequest, Serializable {

  private int gameId;
  private int playerId;
  private MultiplayerGameManager multiplayerGameManager;

  public DisconnectGameRequest(int gameId, int playerId) {
    this.gameId = gameId;
    this.playerId = playerId;
  }

  // TODO Finish implementation
  @Override
  public Response handleRequest() {
    multiplayerGameManager.disconnectFromGame(gameId, playerId);

    return new DefaultResponse(1);
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }
}
