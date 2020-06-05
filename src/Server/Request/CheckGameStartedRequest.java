package Server.Request;

import Server.MultiplayerGameManager;
import Server.Response.BooleanResponse;
import Server.Response.Response;
import java.io.Serializable;

public class CheckGameStartedRequest implements Request, MultiplayerRequest, Serializable {

  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  public CheckGameStartedRequest(int gameId) {
    this.gameId = gameId;
  }

  @Override
  public Response handleRequest() {
    return new BooleanResponse(multiplayerGameManager.getGameById(gameId).isStarted());
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }

}
