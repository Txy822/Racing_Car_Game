package Server.Request;

import GameModes.Tracks.Track;
import Server.Game;
import Server.MultiplayerGameManager;
import Server.Response.CreateGameResponse;
import Server.Response.Response;
import java.io.Serializable;
import java.util.HashMap;

public class CreateGameRequest implements Request, MultiplayerRequest, Serializable {

  private MultiplayerGameManager multiplayerGameManager;
  private String gameName;
  private int maxPlayers;
  private Track track;
  private int laps;

  public CreateGameRequest(String gameName, int maxPlayers, Track track, int laps) {
    this.gameName = gameName;
    this.maxPlayers = maxPlayers;
    this.track = track;
    this.laps = laps;
  }

  public CreateGameRequest() {

  }

  // TODO Finish implementation
  @Override
  public Response handleRequest() {
    Game game = new Game(new HashMap<>(), gameName, maxPlayers, track, laps);

    return new CreateGameResponse(1, multiplayerGameManager.addGame(game));
  }

  public MultiplayerGameManager getMultiplayerGameManager() {
    return multiplayerGameManager;
  }

  @Override
  public void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager) {
    this.multiplayerGameManager = multiplayerGameManager;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String gameName) {
    this.gameName = gameName;
  }
}
