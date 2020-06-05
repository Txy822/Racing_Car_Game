package Server;

import Physics.Vector2D;
import Server.Exceptions.GameStartedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MultiplayerGameManager {

  private Map<Integer, Game> games;
  private String carColour;
  private int gameCounter = 0;

  public MultiplayerGameManager(String carColour) {
    this.games = new HashMap<Integer, Game>();
    this.carColour = carColour;
  }

  public MultiplayerGameManager() {
    this.games = new HashMap<Integer, Game>();
  }

  /**
   * Creates a test game
   *
   * @return the id of the game
   */
  public int addTestGame() {
    return addGame(new Game(new HashMap<>(), "Theo's game!", 5, null,
        3));
  }

  /**
   * Adds a game to the game list
   */
  public int addGame(Game game) {
    gameCounter++;
    game.setId(gameCounter);
    games.put(gameCounter, game);
    return gameCounter;
  }

  /**
   * Connects a player to a game
   *
   * @return the id of the player
   */
  public int connectToGame(int gameId, Vector2D vector, String playerName) {
    int playerId = -1;

    try {
      playerId = games.get(gameId).addPlayer(new Player(playerName, -1, vector, carColour));
    } catch (GameStartedException e) {
      e.printStackTrace();
    }

    return playerId;
  }

  /**
   * Starts a game
   */
  public void startGame(int gameId) {
    try {
      games.get(gameId).startGame();
    } catch (GameStartedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Disconnects a player from a game
   */
  public void disconnectFromGame(int gameId, int playerId) {
    games.get(gameId).removePlayer(playerId);
    if (games.get(gameId).getPlayers().size() == 0) {
      endGame(gameId);
    }
  }

  /**
   * Ends a game
   */
  public void endGame(int gameId) {
    games.get(gameId).setEnded(true);
  }

  /**
   * Returns the player positions of a game
   *
   * @return ArrayList of players
   */
  public ArrayList<Player> getPlayerPositions(int gameId) {
    // Copies the players into an ArrayList. Cloning the individual players in the process
    ArrayList<Player> players = new ArrayList<Player>();

    for (Player player : games.get(gameId).getPlayers().values()) {
      // Instantiates a new clone of a player
      Player clone = new Player(player.getPlayerName(),
          player.getId(), new Vector2D(player.getVector().x, player.getVector().y), carColour);

      // Non constructor attributes
      clone.setLastLaptime(player.getLastLaptime());
      clone.setLapsCompleted(player.getLapsCompleted());

      players.add(clone);
    }

    return players;
  }

  /**
   * Updates the position of a player in a game
   */
  public void updatePlayerPosition(int gameId, int playerId, Vector2D vector) {
    Game game = games.get(gameId);
    game.getPlayers().get(playerId).setVector(vector);
  }

  public Map<Integer, Game> getGames() {
    return games;
  }

  public Game getGameById(int gameId) {
    return new Game(games.get(gameId));
  }

  public Game getGameByIdReference(int gameId) {
    return games.get(gameId);
  }

}
