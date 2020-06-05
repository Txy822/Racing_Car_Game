package Server.Request;

import Physics.Vector2D;
import Server.MultiplayerGameManager;
import Server.Response.ConnectToGameResponse;
import Server.Response.Response;
import java.io.Serializable;

public class JoinGameRequest implements Request, MultiplayerRequest, Serializable {

  private int gameId;
  private Vector2D vector;
  private MultiplayerGameManager multiplayerGameManager;
  private String playerName;

  public JoinGameRequest(int gameId, Vector2D vector, String playerName) {
    this.gameId = gameId;
    this.vector = vector;
    this.playerName = playerName;
  }

  // TODO Finish implementation
  @Override
  public Response handleRequest() {
    return new ConnectToGameResponse(1, multiplayerGameManager.connectToGame(gameId, vector,
        playerName));
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }

}
