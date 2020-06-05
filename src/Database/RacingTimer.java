package Database;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Racing timer class
 */
public class RacingTimer extends Application {

  Timer myTimer = new Timer();
  private Integer timeMilliseconds = 0;

  /**
   * @param primaryStage stage
   */
  @Override
  public void start(Stage primaryStage) {
    Group root = new Group();
    Scene scene = new Scene(root, 600, 400);
    primaryStage.setScene(scene);
    root.getChildren().add(timeCounter());
    primaryStage.show();
  }

  /**
   * Convert millisecond to hours format
   *
   * @param duration time in milli
   * @return hours format
   */
  public String msToTime(int duration) {
    int milliseconds = ((duration % 1000)), seconds = ((duration / 1000) % 60), minutes = (
        (duration / (1000 * 60)) % 60), hours = ((duration / (1000 * 60 * 60)) % 24);

    hours = (hours < 10) ? Integer.parseInt("0" + hours) : hours;
    minutes = (minutes < 10) ? Integer.parseInt("0" + minutes) : minutes;
    seconds = (seconds < 10) ? Integer.parseInt("0" + seconds) : seconds;

    return hours + ":" + minutes + ":" + seconds + "." + milliseconds;
  }

  /**
   * Convert hours format to milli
   *
   * @param HHMMSSms hours format
   * @return millis
   */
  public int timeToms(String HHMMSSms) {
    int totalMilliSeconds = 0;
    String[] storeHHMMSSms = HHMMSSms.split(":");
    int hour = Integer.parseInt(storeHHMMSSms[0]);
    int minute = Integer.parseInt(storeHHMMSSms[1]);
    int second = Integer.parseInt(storeHHMMSSms[2]);
    int milliseconds = Integer.parseInt(storeHHMMSSms[3]);

    totalMilliSeconds = milliseconds + 1000 * second + (1000 * 60 * minute) + (1000 * 3600 * hour);

    return totalMilliSeconds;
  }

  /**
   * Updating the counter
   *
   * @param timerLabel Label
   */
  public void updateTimer(Label timerLabel) {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> {
          timeMilliseconds++;
          String time = msToTime(timeMilliseconds);
          timerLabel.setText(time);
          setTime(timeMilliseconds);

        });
      }
    };
    myTimer.scheduleAtFixedRate(task, 1, 1);

  }

  /**
   * Stops timer
   */
  public void stop() {
    myTimer.cancel();
  }

  /**
   * Get time value
   *
   * @return time in millis
   */
  public int getTime() {
    return timeMilliseconds;
  }

  /**
   * Set time
   *
   * @param timeMilliseconds time
   */
  public void setTime(int timeMilliseconds) {
    this.timeMilliseconds = timeMilliseconds;
  }

  /**
   * Vbox for counter
   *
   * @return Vbox
   */
  public VBox timeCounter() {
    Label timerLabel = new Label();
    timerLabel.setText(timeMilliseconds.toString());
    timerLabel.setLayoutX(200);
    timerLabel.setLayoutY(200);
    timerLabel.setTextFill(Color.BLUE);

    updateTimer(timerLabel);
    VBox vb = new VBox(20);
    // center the components within VBox
    vb.setAlignment(Pos.CENTER);
    vb.getChildren().addAll(timerLabel);

    return vb;
  }
}
