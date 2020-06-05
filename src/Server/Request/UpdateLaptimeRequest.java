package Server.Request;

import Server.MultiplayerGameManager;
import Server.Response.DefaultResponse;
import Server.Response.Response;
import java.io.Serializable;

public class UpdateLaptimeRequest implements Request, MultiplayerRequest, Serializable {

  private MultiplayerGameManager multiplayerGameManager;
  private int playerId;
  private int gameId;
  private long time;

  public UpdateLaptimeRequest(int playerId, int gameId, long time) {
    this.playerId = playerId;
    this.gameId = gameId;
    this.time = time;
  }

  @Override
  public Response handleRequest() {
    multiplayerGameManager.getGameByIdReference(gameId).getPlayers().get(playerId)
        .setLastLaptime(time);
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
