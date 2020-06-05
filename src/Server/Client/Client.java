package Server.Client;

import static Server.Server.PORT;

import GameModes.Tracks.Track;
import Physics.Vector2D;
import Server.Game;
import Server.Player;
import Server.Request.CheckGameStartedRequest;
import Server.Request.CloseConnectionRequest;
import Server.Request.CreateGameRequest;
import Server.Request.DisconnectGameRequest;
import Server.Request.EndGameRequest;
import Server.Request.GetGameByIdRequest;
import Server.Request.GetGamesRequest;
import Server.Request.GetLeaderboardRequest;
import Server.Request.GetPlayerPositionsRequest;
import Server.Request.InitRequest;
import Server.Request.JoinGameRequest;
import Server.Request.LoginRequest;
import Server.Request.RegisterRequest;
import Server.Request.StartGameRequest;
import Server.Request.UpdateLaptimeRequest;
import Server.Request.UpdatePlayerPositionRequest;
import Server.Response.BooleanResponse;
import Server.Response.ConnectToGameResponse;
import Server.Response.CreateGameResponse;
import Server.Response.DefaultResponse;
import Server.Response.GameResponse;
import Server.Response.GetGamesResponse;
import Server.Response.GetLeaderboardResponse;
import Server.Response.GetPlayerPositionsResponse;
import Server.Response.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Class used to perform network functions. No more than a single instance should be used.
 */
public class Client {

  private String hostname;
  private Socket socket;
  private ObjectOutputStream toServer;
  private ObjectInputStream fromServer;

  public Client(String hostname) {
    this.hostname = hostname;
  }

  /**
   * Connects to the server Must be called before anything else
   *
   * @return true if connection is successful otherwise false
   */
  public boolean connect() {
    System.out.println("Client loading");

    try {
      socket = new Socket(hostname, PORT);

      // Instantiates input and output object streams
      toServer = new ObjectOutputStream(socket.getOutputStream());
      fromServer = new ObjectInputStream(socket.getInputStream());

      toServer.writeObject(new InitRequest());

      Response response = (Response) fromServer.readObject();
      System.out.println("Loading done");

      return response.getCode() == 1;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Safely closes the connection to the server, only call when shutting down the game.
   */
  public void closeConnection() {
    try {
      toServer.writeObject(new CloseConnectionRequest());
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Attempts to login a user in
   *
   * @param username the username of the person you want to login
   * @param password the password of the person you want to login
   * @return true if login is successful otherwise false.
   */
  @Deprecated
  public boolean login(String username, String password) {
    try {
      toServer.writeObject(new LoginRequest(username, password));

      Response response = (Response) fromServer.readObject();
      return response.getCode() == 1;

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Registers a new users in the database
   *
   * @param username the username of the person you want to register.
   * @param password the password of the person you want to register.
   * @param email the email of the person you want to register.
   * @return true if registration was successful, false otherwise.
   */
  @Deprecated
  public boolean register(String username, String password, String email) {
    try {
      toServer.writeObject(new RegisterRequest(username, password, email));

      Response response = (Response) fromServer.readObject();
      return response.getCode() == 1;

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Gets the leaderboard for a given track
   *
   * @param trackId the id of the track you want the leaderboard for.
   * @return ArrayList of Time objects
   */
  @Deprecated
  public ArrayList<Database.Time> getLeaderboard(int trackId) {
    try {
      toServer.writeObject(new GetLeaderboardRequest(trackId));

      GetLeaderboardResponse response = (GetLeaderboardResponse) fromServer.readObject();
      return response.getLeaderboard();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Retrieves the game state for a given gameId
   *
   * @return The game state
   */
  public Game getGameById(int gameId) {
    try {
      toServer.writeObject(new GetGameByIdRequest(gameId));

      GameResponse response = (GameResponse) fromServer.readObject();
      return response.getGame();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * Starts a game
   */
  public void startGame(int gameId) {
    try {
      toServer.writeObject(new StartGameRequest(gameId));

      fromServer.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Ends the game
   */
  public void endGame(int gameId) {
    try {
      toServer.writeObject(new EndGameRequest(gameId));

      fromServer.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if a game has started
   */
  public boolean checkGameStarted(int gameId) {
    try {
      toServer.writeObject(new CheckGameStartedRequest(gameId));

      BooleanResponse response = (BooleanResponse) fromServer.readObject();
      return response.isBool();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Gets all the player positions for a game Deprecated use getGameById instead
   */
  @Deprecated
  public ArrayList<Player> getPlayerPositions(int gameId) {
    try {
      toServer.writeObject(new GetPlayerPositionsRequest(gameId));

      GetPlayerPositionsResponse response = (GetPlayerPositionsResponse) fromServer.readObject();

      return response.getPositions();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();

      return new ArrayList<>();
    }
  }

  /**
   * Updates the laptime for a player in a multiplayer game
   */
  public void updateLaptime(int playerId, int gameId, long time) {
    try {
      toServer.writeObject(new UpdateLaptimeRequest(playerId, gameId, time));

      Response response = (DefaultResponse) fromServer.readObject();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Updates the players position in a multiplayer game
   */
  public void updatePlayerPosition(int playerId, int gameId, Vector2D vector) {
    try {
      toServer.writeObject(new UpdatePlayerPositionRequest(gameId, playerId, vector));

      Response response = (DefaultResponse) fromServer.readObject();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Connects the player to a game
   */
  public int connectToGame(Vector2D gameObject, int gameId, String playerName) {
    try {
      toServer.writeObject(new JoinGameRequest(gameId, gameObject, playerName));

      ConnectToGameResponse response = (ConnectToGameResponse) fromServer.readObject();
      return response.getPlayerId();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return -1;
  }

  /**
   * Disconnects a player from a game
   */
  public void disconnectFromGame(int gameId, int playerId) {
    try {
      toServer.writeObject(new DisconnectGameRequest(gameId, playerId));

      Response response = (DefaultResponse) fromServer.readObject();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a new multiplayer game
   *
   * @return the id of the game created
   */
  public int createGame(String gameName, int maxPlayers, Track track, int laps) {
    try {
      CreateGameRequest request = new CreateGameRequest(gameName, maxPlayers, track, laps);
      request.setGameName(gameName);
      toServer.writeObject(request);

      CreateGameResponse response = (CreateGameResponse) fromServer.readObject();

      return response.getGameId();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return -1;
  }

  /**
   * Gets a list of all the current multiplayer games
   *
   * @return ArrayList of Game
   */
  public ArrayList<Game> getGames() {
    try {
      toServer.writeObject(new GetGamesRequest());
      GetGamesResponse response = (GetGamesResponse) fromServer.readObject();

      return response.getGames();

    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }
}
