package Server.Client;

public class Time {

  private double time;
  private String username; //Change to User class
  private int userId;

  /**
   * @param time
   * @param username
   * @param userId
   */
  public Time(double time, String username, int userId) {
    this.time = time;
    this.username = username;
    this.userId = userId;
  }

  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  // TODO Finish Time toString()
  @Override
  public String toString() {
    return "";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != Time.class) {
      return false;
    } else {
      Time timeObj = (Time) obj;
      return timeObj.time == time && username.equals(timeObj.username) && userId == timeObj.userId;
    }
  }
}
