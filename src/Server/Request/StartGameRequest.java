package Server.Request;

import Server.MultiplayerGameManager;
import Server.Response.DefaultResponse;
import Server.Response.Response;
import java.io.Serializable;

public class StartGameRequest implements Request, MultiplayerRequest, Serializable {

  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  public StartGameRequest(int gameId) {
    this.gameId = gameId;
  }

  @Override
  public Response handleRequest() {
    multiplayerGameManager.startGame(gameId);

    return new DefaultResponse(0);
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }
}
