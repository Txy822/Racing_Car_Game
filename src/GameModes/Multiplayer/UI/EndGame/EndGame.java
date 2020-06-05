package GameModes.Multiplayer.UI.EndGame;

import GameModes.Multiplayer.MultiplayerSceneManager;
import Server.Game;
import Server.Player;
import UI.Leaderboard;
import UI.MenuManager;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EndGame {

  protected static String podBGSrc = "src/Resources/BG/PodiumBG.png";
  protected static String backSrc = "src/Resources/Button/backButton.png";

  //Car images filepath
  protected static String redCarSrc = "src/Resources/Cars/CarRed.png";
  protected static String blueCarSrc = "src/Resources/Cars/CarBlue.png";
  protected static String greenCarSrc = "src/Resources/Cars/CarGreen.png";
  protected static String yellowCarSrc = "src/Resources/Cars/CarYellow.png";

  protected static String tableCss = "EndGame.css";

  private static Stage stage;
  private static MenuManager ui;
  private static ObservableList<Leaderboard.Racer> data = FXCollections.observableArrayList();
  private int playerId;
  private BorderPane insScreen;
  private MultiplayerSceneManager multiplayerSceneManager;
  private Game game;

  public EndGame(Game game, MultiplayerSceneManager multiplayerSceneManager, int playerId,
      MenuManager ui) {
    this.multiplayerSceneManager = multiplayerSceneManager;
    this.game = game;
    this.stage = multiplayerSceneManager.getPrimaryStage();
    this.playerId = playerId;
    this.ui = ui;
    insScreen = new BorderPane();
  }

  public Scene create() throws Exception {

    TableView<Leaderboard.Racer> table = new TableView<Leaderboard.Racer>();

    Scene scene = new Scene(insScreen, 1920, 1080);

    table.setEditable(true);
    table.setStyle(".column-header {-fx-background-color: transparent; } ");
    String css = EndGame.class.getResource(tableCss).toExternalForm();
    scene.getStylesheets().clear();
    scene.getStylesheets().add(css);
    table.setMinHeight(700);
    table.setTranslateY(50);

    TableColumn nameCol = new TableColumn();
    nameCol.setMinWidth(250);
    nameCol.setCellValueFactory(new PropertyValueFactory<Leaderboard.Racer, String>("name"));
    nameCol.setSortable(false);

    TableColumn timeCol = new TableColumn("Time");
    timeCol.setMinWidth(100);
    timeCol.setCellValueFactory(new PropertyValueFactory<Leaderboard.Racer, String>("time"));
    timeCol.setSortType(TableColumn.SortType.DESCENDING);

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.setTranslateX(-150);
    vbox.getChildren().addAll(table);

    HBox exitButton = ui.addHbox(stage);
    exitButton.setAlignment(Pos.BASELINE_RIGHT);
    exitButton.setTranslateX(-100);

    initializeLeaderboard();
    assemblePodium();

    table.setItems(data);
    table.getColumns().addAll(nameCol, timeCol);

    Button leaveButton = new Button();
    leaveButton.setOnAction(event -> {
      try {
        multiplayerSceneManager.leaveGame(game, playerId);
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    Image image = new Image(getClass().getResourceAsStream("/Resources/Button/backButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(image.getHeight() * 0.8);
    imageView.setFitWidth(image.getWidth() * 0.8);
    imageView.setPreserveRatio(true);

    leaveButton.setStyle("-fx-background-color: none;\n"
        + "  -fx-border-color: none;");

    leaveButton.setGraphic(imageView);
    exitButton.getChildren().add(leaveButton);

    Background bg = ui
        .addBackground(podBGSrc, multiplayerSceneManager.WIDTH, multiplayerSceneManager.HEIGHT);

    insScreen.setRight(vbox);
    insScreen.setBottom(exitButton);
    insScreen.setBackground(bg);

    return scene;
  }

  private void initializeLeaderboard() {
    for (Player player : game.getSortedTimes()) {
      data.add(new Leaderboard.Racer(player.getPlayerName(), player.millisToMmSs()));
    }
  }

  private void assemblePodium() throws Exception {
    HBox hbox = new HBox();
    hbox.setSpacing(80);
    hbox.setTranslateX(310);
    hbox.setTranslateY(-190);

    ArrayList<Player> players = game.getSortedTimes();
    if (players.size() >= 3) {
      Player player1 = players.get(0);
      Player player2 = players.get(1);
      Player player3 = players.get(2);

      VBox podium1 = podiumPos(player1.getPlayerName(), player1.getCarColour());

      VBox podium2 = podiumPos(player2.getPlayerName(), player2.getCarColour());
      podium2.setTranslateY(130);

      VBox podium3 = podiumPos(player3.getPlayerName(), player3.getCarColour());
      podium3.setTranslateY(200);

      hbox.getChildren().addAll(podium2, podium1, podium3);

      insScreen.setLeft(hbox);
    }
  }

  private VBox podiumPos(String playerName, String carImgSrc) throws Exception {
    Text name = new Text(playerName);
    name.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 20));
    ImageView place = ui.newImage(carImgSrc, 1);
    VBox vbox = new VBox(15);

    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().addAll(name, place);
    return vbox;
  }
}
