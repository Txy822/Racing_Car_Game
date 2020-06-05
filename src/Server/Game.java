package Server;

import GameModes.Tracks.Track;
import Physics.Vector2D;
import Server.Exceptions.GameStartedException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Game implements Serializable {

  private HashMap<Integer, Player> players;
  private Vector2D startPosition = new Vector2D(150, 150);
  private Track track;

  private int maxPlayers;
  private boolean started;
  private boolean ended;
  private String gameName;
  private int id;
  private int playerCounter = 0;
  private int laps;

  public Game(HashMap<Integer, Player> players, String gameName, int maxPlayers,
      Track track, int laps) {
    this.players = players;
    this.gameName = gameName;
    this.maxPlayers = maxPlayers;
    this.track = track;
    this.laps = laps;
    started = false;
    ended = false;
  }

  /**
   * Copy constructor
   */
  public Game(Game game) {
    // Performs a deep copy of the players HashMap
    HashMap<Integer, Player> tempPlayers = new HashMap<>();

    for (Player player : game.getPlayers().values()) {
      Player clone = new Player(player);
      tempPlayers.put(clone.getId(), clone);
    }

    this.players = tempPlayers;
    this.maxPlayers = game.getMaxPlayers();
    this.started = game.isStarted();
    this.ended = game.isEnded();
    this.gameName = game.getGameName();
    this.id = game.getId();
    this.playerCounter = game.getPlayerCounter();
    this.startPosition = game.getStartPosition();
    this.track = game.getTrack();
    this.laps = game.getLaps();
  }

  public void setStartPositions() {
    int counter = 0;

    // Iterates over the players map and sets the start positions incrementally so that they are
    // spaced out on the start line
    for (Player player : players.values()) {
      player.setVector(new Vector2D(startPosition.x - 35 * counter++, startPosition.y));
    }
  }

  public HashMap<Integer, Player> getPlayers() {
    return players;
  }

  public int addPlayer(Player player) throws GameStartedException {
    if (started) {
      throw new GameStartedException("Cannot join a game in progress.");
    }

    player.setId(playerCounter++);

    players.put(player.getId(), player);
    return player.getId();
  }

  public HashMap<Integer, Player> removePlayer(int playerId) {
    players.remove(playerId);
    return players;
  }

  public boolean hasPlayer(int playerId) {
    return players.containsKey(playerId);
  }

  public void startGame() throws GameStartedException {
    if (started) {
      throw new GameStartedException("A started game can not be started again.");
    }
    setStartPositions();

    started = true;
  }

  public String getGameName() {
    return gameName;
  }

  public void setGameName(String game1Name) {
    this.gameName = game1Name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isStarted() {
    return started;
  }

  public Vector2D getStartPosition() {
    return startPosition;
  }

  public void setStartPosition(Vector2D startPosition) {
    this.startPosition = startPosition;
  }

  public int getMaxPlayers() {
    return maxPlayers;
  }

  public void setMaxPlayers(int maxPlayers) {
    this.maxPlayers = maxPlayers;
  }

  public Track getTrack() {
    return track;
  }

  public void setTrack(Track track) {
    this.track = track;
  }

  public int getPlayerCounter() {
    return playerCounter;
  }

  public int getLaps() {
    return laps;
  }

  public void setLaps(int laps) {
    this.laps = laps;
  }

  public boolean isEnded() {
    return ended;
  }

  public void setEnded(boolean ended) {
    this.ended = ended;
  }

  /**
   * Returns the Players sorted with the fastest times
   */
  public ArrayList<Player> getSortedTimes() {
    // Converts the Map of players to an ArrayList of players
    ArrayList<Player> temp = new ArrayList<>();

    for (Player player : players.values()) {
      temp.add(new Player(player));
    }

    // Sorts the ArrayList
    temp.sort((o1, o2) -> {
      if (o1.getLapsCompleted() > o2.getLapsCompleted()) {
        return -1;
      }

      if (o1.getLapsCompleted() < o2.getLapsCompleted()) {
        return 1;
      }

      return Long.compare(o1.getLastLaptime(), o2.getLastLaptime());

    });

    return temp;
  }
}
