package Database;

public class Time {

  private int time;
  private String username; //Change to User class

  private int userId;
  private int rank;

  public Time(int time, String username, int userId) {
    this.time = time;
    this.username = username;
    this.userId = userId;
  }

  public Time(int time, String username, int userId, int rank) {
    this.time = time;
    this.username = username;
    this.userId = userId;
    this.rank = rank;
  }


  public int getRank() {
    return rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}

