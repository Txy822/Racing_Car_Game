package GameModes.Multiplayer;

import GameModes.Multiplayer.GameModes.MultiplayerGame;
import GameModes.Multiplayer.UI.EndGame.EndGame;
import GameModes.Multiplayer.UI.GameBrowser.GameBrowser;
import GameModes.Multiplayer.UI.GameLobby.GameLobby;
import Physics.Vector2D;
import Server.Client.Client;
import Server.Game;
import UI.GameMenu;
import UI.MenuManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.stage.Stage;

public class MultiplayerSceneManager {

  public final double WIDTH = widthVal();
  public final double HEIGHT = heightVal();
  private Stage primaryStage;
  private String playerName = "Player";
  private Client client;
  private MenuManager ui;

  public MultiplayerSceneManager(Stage primaryStage, MenuManager ui) {
    this.primaryStage = primaryStage;
    this.ui = ui;

    // Creates the client and connects to the server
    client = new Client("localhost");
    client.connect();
  }

  public static double widthVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getWidth();
  }

  public static double heightVal() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    return screenSize.getHeight();
  }

  /**
   * Shows the GameBrowser scene
   */
  public void show() throws Exception {
    GameBrowser gameBrowser = new GameBrowser(client, this, ui);

    primaryStage.setScene(gameBrowser.create());
  }

  /**
   * Joins a game and displays the GameLobby
   */
  public void joinGame(Game game) {
    int playerId = client.connectToGame(new Vector2D(0, 0), game.getId(), playerName);
    GameLobby gameLobby = new GameLobby(client.getGameById(game.getId()), client, playerId,
        this, ui);
    primaryStage.setScene(gameLobby.create());
  }

  /**
   * Leaves the game and displays the GameBrowser
   */
  public void leaveGame(Game game, int playerId) throws Exception {
    client.disconnectFromGame(game.getId(), playerId);

    GameBrowser gameBrowser = new GameBrowser(client, this, ui);

    primaryStage.setScene(gameBrowser.create());
  }

  /**
   * Starts a game and displays the ingame screen
   */
  public void startGame(Game game, int playerId) {
    MultiplayerGame multiplayerGame = new MultiplayerGame(game, playerId, client, this);
    multiplayerGame.start();

    primaryStage.setScene(multiplayerGame.create());
  }

  /**
   * Ends a game and displays the EndGame screen
   */
  public void endGame(Game game, int playerId) throws Exception {
    EndGame endGame = new EndGame(game, this, playerId, ui);
    primaryStage.setScene(endGame.create());
  }

  /**
   * Displays the GameMenu
   */
  public void returnToGameSelection() throws Exception {
    GameMenu game = new GameMenu(primaryStage,
        new MenuManager(primaryStage, ui.getCarCol(), ui.getID()));
    primaryStage.setScene(game.create(primaryStage));
  }

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public String getCarColour() {
    return ui.getCarCol();
  }

}
