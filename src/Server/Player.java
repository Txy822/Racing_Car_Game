package Server;

import Physics.Vector2D;
import java.io.Serializable;

public class Player implements Serializable {

  //Car images filepath
  private static String redCarSrc = "src/Resources/Cars/CarRed.png";
  private static String blueCarSrc = "src/Resources/Cars/CarBlue.png";
  private static String greenCarSrc = "src/Resources/Cars/CarGreen.png";
  private static String yellowCarSrc = "src/Resources/Cars/CarYellow.png";

  private String playerName;
  private String carColour = "Red";
  private int id;
  private Vector2D vector;
  private long lastLaptime;
  private int lapsCompleted;

  public Player(String playerName, int id, Vector2D vector, String carColour) {
    this.playerName = playerName;
    this.id = id;
    this.vector = vector;
    this.carColour = carColour;
  }

  public Player(Player temp) {
    this.playerName = temp.getPlayerName();
    this.id = temp.getId();
    this.vector = new Vector2D(temp.getVector().x, temp.getVector().y);
    this.lastLaptime = temp.getLastLaptime();
    this.lapsCompleted = temp.getLapsCompleted();
    this.carColour = temp.getCarColour();
  }

  @Override
  public boolean equals(Object player) {
    if (player == null) {
      return false;
    }
    return ((Player) player).getId() == id
        && ((Player) player).getVector().x == vector.x
        && ((Player) player).getVector().y == vector.y;
  }

  /**
   * Converts the lap time from millis to a string representation
   *
   * @return String MM:SS
   */
  public String millisToMmSs() {
    long minutes = Math.abs((lastLaptime / 1000) / 60);
    int seconds = Math.abs(((int) ((lastLaptime / 1000) % 60)));

    return (lastLaptime < 0 ? "-" : "") + (minutes < 10 ? "0" + minutes : minutes) + ":" + (
        seconds < 10 ? "0" + seconds : seconds);
  }

  public Vector2D getVector() {
    return vector;
  }

  public void setVector(Vector2D vector) {
    this.vector = vector;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getCarColour() {
    return carColour;
  }

  public void setCarColour(String carColour) {
    this.carColour = carColour;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  public long getLastLaptime() {
    return lastLaptime;
  }

  public void setLastLaptime(long lastLaptime) {
    this.lastLaptime = lastLaptime;
    lapsCompleted++;
  }

  public int getLapsCompleted() {
    return lapsCompleted;
  }

  public void setLapsCompleted(int lapsCompleted) {
    this.lapsCompleted = lapsCompleted;
  }
}
