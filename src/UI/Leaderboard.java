package UI;

import Database.DatabaseControl;
import Database.RacingTimer;
import Database.Time;
import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Leaderboard extends MenuManager {

  protected static double scl = 1;
  protected static String leadBGSrc = "src/Resources/BG/leaderboardBG.png";
  protected static String backSrc = "src/Resources/Button/backButton.png";
  protected static String tableCss = "CarGame.css";

  private static ObservableList<Racer> data = FXCollections.observableArrayList();


  private static ArrayList<Integer> tracks = new ArrayList<>();

  public Leaderboard(Stage primaryStage, String carColour) {
    super(primaryStage, carColour);
  }

  /**
   * create method for Leaderboard
   */
  public static Scene create(Stage stage) throws Exception {
    TableView<Racer> table = new TableView<Racer>();
    double width = widthVal();
    double height = (width / 16) * 9;

    BorderPane insScreen = new BorderPane();
    Scene scene = new Scene(insScreen, 1920, 1080);

    table.setEditable(true);
    table.setStyle(".column-header {-fx-background-color: transparent; } ");
    String css = Leaderboard.class.getResource(tableCss).toExternalForm();
    scene.getStylesheets().clear();
    scene.getStylesheets().add(css);
    table.setTranslateX(230);
    table.setMinHeight(700);

    TableColumn idCol = new TableColumn("ID");
    idCol.setMinWidth(250);
    idCol.setCellValueFactory(new PropertyValueFactory<Racer, String>("id"));
    idCol.setSortable(false);

    TableColumn nameCol = new TableColumn("Name");
    nameCol.setMinWidth(250);
    nameCol.setCellValueFactory(new PropertyValueFactory<Racer, String>("name"));
    nameCol.setSortable(false);

    TableColumn timeCol = new TableColumn("Time");
    timeCol.setMinWidth(150);
    timeCol.setCellValueFactory(new PropertyValueFactory<Racer, String>("time"));
    timeCol.setSortType(TableColumn.SortType.DESCENDING);

    TableColumn trackCol = new TableColumn("Track");
    trackCol.setMinWidth(150);
    trackCol.setCellValueFactory(new PropertyValueFactory<Racer, String>("track"));

    TableColumn rankCol = new TableColumn("Rank");
    rankCol.setMinWidth(150);
    rankCol.setCellValueFactory(new PropertyValueFactory<Racer, String>("rank"));

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(table);

    Background bg = addBackground(leadBGSrc, width, height);
    HBox exitButton = addHbox(stage);

    Button exit = newButton(backSrc, 0.8);
    exit.setOnAction(e -> {
      try {
        stage.setScene(MenuManager.create(stage));
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    });

    exitButton.getChildren().add(exit);

    insScreen.setLeft(vbox);
    insScreen.setBottom(exitButton);
    insScreen.setBackground(bg);

    try {
      initializeLeaderboard();
      table.setItems(data);
      table.getColumns().addAll(nameCol, idCol, timeCol, trackCol, rankCol);
      table.setPlaceholder(new Label("Empty Leaderboards"));
    } catch (Exception e1) {
      table.getColumns().addAll(nameCol, idCol, timeCol, trackCol, rankCol);
      table.setPlaceholder(new Label("Offline Leaderboards"));
      return scene;
    }
    return scene;
  }

  /**
   * collect and add all leaderboard information from database
   */
  public static void initializeLeaderboard() {
    ArrayList<Database.Time> list = new ArrayList<>();
    DatabaseControl db = new DatabaseControl();
    list = db.getLeaderBoard(new Leaderboard(primaryStage, carColour).getTracks(1));
    for (Time value : list) {

      String id = Integer.toString(value.getUserId());
      int track = db.getPlayerTrackIDFromLeaderBoard(value.getUserId());
      String username = value.getUsername();
      String rank = Integer.toString(value.getRank());
      String time = new RacingTimer().msToTime(value.getTime());

      Racer playerToAdd = new Racer(username, time, track, id, rank);
      data.add(playerToAdd);
    }
  }

  /**
   * create an arraylist of 3 tracks
   */
  public ArrayList<Integer> getTracks(int gameID) {
    int numberofTracks = 3;
    for (int i = 1; i <= numberofTracks; i++) {
      tracks.add(i);
    }
    return tracks;
  }

  /**
   * Racer object class used to input new leaderboard data from initialize leaderboard method
   */
  public static class Racer {

    private final SimpleStringProperty name;
    private final SimpleStringProperty time;
    private final SimpleStringProperty id;
    private final SimpleStringProperty rank;
    private final SimpleIntegerProperty track;


    public Racer(String name, String time, int track, String id, String rank) {
      this.name = new SimpleStringProperty(name);
      this.time = new SimpleStringProperty(time);
      this.track = new SimpleIntegerProperty(track);
      this.id = new SimpleStringProperty(id);
      this.rank = new SimpleStringProperty(rank);
    }

    public Racer(String name, String time) {
      this.name = new SimpleStringProperty(name);
      this.time = new SimpleStringProperty(time);
      this.id = new SimpleStringProperty("0");
      this.rank = new SimpleStringProperty("1");
      this.track = new SimpleIntegerProperty(1);
    }

    public String getName() {
      return name.get();
    }

    public void setName(String val) {
      name.set(val);
    }

    public String getTime() {
      return time.get();
    }

    public void setTime(String val) {
      time.set(val);
    }

    public Integer getTrack() {
      return track.get();
    }

    public void setTrack(int val) {
      track.set(val);
    }

    public String getId() {
      return id.get();
    }

    public void setID(String val) {
      id.set(val);
    }

    public String getRank() {
      return rank.get();
    }

    public void setRank(String val) {
      rank.set(val);
    }
  }
}