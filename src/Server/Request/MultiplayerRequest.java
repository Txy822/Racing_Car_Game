package Server.Request;

import Server.MultiplayerGameManager;

public interface MultiplayerRequest {

  /**
   * Allows injection of a MultiplayerGameManager instance into the request
   */
  void setMultiplayerGameManager(MultiplayerGameManager multiplayerGameManager);
}
