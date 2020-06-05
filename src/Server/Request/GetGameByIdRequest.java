package Server.Request;

import Server.Game;
import Server.MultiplayerGameManager;
import Server.Response.GameResponse;
import Server.Response.Response;
import java.io.Serializable;

public class GetGameByIdRequest implements Request, Serializable, MultiplayerRequest {

  private MultiplayerGameManager multiplayerGameManager;
  private int gameId;

  public GetGameByIdRequest(int gameId) {
    this.gameId = gameId;
  }

  @Override
  public Response handleRequest() {
    Game temp = multiplayerGameManager.getGameById(gameId);

    return new GameResponse(temp);
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }
}
