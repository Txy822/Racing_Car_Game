package GameModes.Multiplayer.UI.GameBrowser.GameItem;

import Server.Game;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class GameItem {

  private Game game;

  @FXML
  private HBox root;

  @FXML
  private Text nameText;

  @FXML
  private Button joinButton;

  @FXML
  private StackPane nameJoinStackPane;

  @FXML
  private Text playerCountText;

  @FXML
  private HBox rightBox;

  @FXML
  private Text lapText;

  @FXML
  private Text trackText;

  @FXML
  private Text startedText;

  public GameItem(Game game) {
    this.game = game;
  }

  /**
   * Creates the Scene graph
   *
   * @return Scene graph
   */
  public Node create() {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(GameItem.class.getResource("GameItem.fxml"));
    loader.setController(this);

    try {
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }

    root.getStylesheets().add(GameItem.class.getResource("GameItem.css").toExternalForm());

    nameText.setText(game.getGameName());

    if (game.isStarted()) {
      startedText.setText(" (In progress)");
      startedText.setFill(Color.RED);
    }

    lapText.setText(game.getLaps() + (game.getLaps() == 1 ? " lap" : " laps")); // Checks plurality

    trackText.setText(game.getTrack().toString());
    trackText.setFill(Color.RED);

    playerCountText.setText(game.getPlayers().size() + "/" + game.getMaxPlayers() + " players");

    Image image = new Image(getClass().getResourceAsStream("/Resources/Button/playButton.png"));
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(100);
    imageView.setPreserveRatio(true);

    joinButton.setGraphic(imageView);

    rightBox.setSpacing(5);

    root.setAlignment(Pos.CENTER);
    rightBox.setAlignment(Pos.CENTER);

    return root;
  }

  public Button getJoinButton() {
    return joinButton;
  }
}
