package Server.Client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import Physics.Vector2D;
import Server.Game;
import org.junit.Before;
import org.junit.Test;

// Server has to be running for these tests to pass

public class ClientTest {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void connect() {
    Client client = new Client("localhost");
    client.connect();
    client.closeConnection();

  }

  /*
  @Test(expected = IOException.class)
  public void connectInvalidHost() {
    Client client = new Client("wronghostname");
    client.connect();
    client.closeConnection();
  }
  */

  @Test
  public void closeConnection() {
    Client client = new Client("localhost");
    client.connect();
    client.closeConnection();
  }

  @Test
  public void login() {
  }

  @Test
  public void register() {
  }

  @Test
  public void getLeaderboard() {
  }

  @Test
  public void updateLeaderboard() {
  }

  @Test
  public void getGameById() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    assertEquals(client.getGameById(gameId).getGameName(), game.getGameName());
  }

  @Test
  public void startGame() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    client.startGame(gameId);

    assertTrue(client.getGameById(gameId).isStarted());

  }

  @Test
  public void endGame() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    client.endGame(gameId);

    assertTrue(client.getGameById(gameId).isEnded());
  }

  @Test
  public void checkGameStarted() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    assertFalse(client.checkGameStarted(gameId));
  }

  @Test
  public void getPlayerPositions() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    client.connectToGame(new Vector2D(0, 0), gameId, "Test");

    assertTrue(client.getPlayerPositions(gameId).size() > 0);
  }

  @Test
  public void updateLaptime() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    int playerId = client.connectToGame(new Vector2D(0, 0), gameId, "Test");
    client.updateLaptime(playerId, gameId, 100);

    assertEquals(client.getGameById(gameId).getPlayers().get(playerId).getLastLaptime(), 100);
  }

  @Test
  public void updatePlayerPosition() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    int playerId = client.connectToGame(new Vector2D(0, 0), gameId, "Test");

    Vector2D pos = new Vector2D(5, 5);

    client.updatePlayerPosition(playerId, gameId, pos);

    assertEquals(client.getPlayerPositions(gameId).get(0).getVector().x, pos.x, 1);
    assertEquals(client.getPlayerPositions(gameId).get(0).getVector().y, pos.y, 1);
  }

  @Test
  public void connectToGame() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    client.connectToGame(new Vector2D(0, 0), gameId, "Test");
    assertTrue(client.getGameById(gameId).getPlayers().size() > 0);
  }

  @Test
  public void disconnectFromGame() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    client.disconnectFromGame(gameId, client.connectToGame(new Vector2D(0, 0), gameId, "Test"));
  }

  @Test
  public void createGame() {
    Client client = new Client("localhost");
    client.connect();

    int gameId = client.createGame("Test", 1, null, 3);

    Game game = new Game(null, "Test", 1, null, 3);

    client.createGame(null, 1, null, 3);
  }

  @Test
  public void getGames() {
    Client client = new Client("localhost");
    client.connect();

    client.getGames();
  }
}