package Server.Request;

import Physics.Vector2D;
import Server.MultiplayerGameManager;
import Server.Response.DefaultResponse;
import Server.Response.Response;
import java.io.Serializable;

public class UpdatePlayerPositionRequest implements Request, MultiplayerRequest, Serializable {

  private int gameId;
  private int playerId;
  private Vector2D vector;
  private MultiplayerGameManager multiplayerGameManager;

  public UpdatePlayerPositionRequest(int gameId, int playerId, Vector2D vector) {
    this.gameId = gameId;
    this.playerId = playerId;
    this.vector = vector;
  }

  @Override
  public Response handleRequest() {
    multiplayerGameManager.updatePlayerPosition(gameId, playerId, vector);

    return new DefaultResponse(1);
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }
}
