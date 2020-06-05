package Database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TimeTest {

  private int time = 45;
  private int rank = 1;
  private int id = 100;
  private String username = "username";


  @Test
  public void getRank() {
    Time t = new Time(time, username, id, rank);
    assertEquals(t.getRank(), rank);
  }

  @Test
  public void setRank() {
    int newRank = 2;
    Time t = new Time(time, username, id, rank);
    t.setRank(newRank);
    assertEquals(newRank, t.getRank());
  }

  @Test
  public void getUserId() {
    Time t = new Time(time, username, id, rank);
    assertEquals(t.getUserId(), id);
  }

  @Test
  public void setUserId() {
    int id = 111;
    Time t = new Time(time, username, id, rank);
    t.setRank(2);
    assertEquals(t.getUserId(), id);
  }

  @Test
  public void getTime() {
    Time t = new Time(time, username, id, rank);
    assertEquals(t.getTime(), time);
  }

  @Test
  public void setTime() {
    int newTime = 41;
    Time t = new Time(time, username, id, rank);
    t.setTime(newTime);
    assertEquals(t.getTime(), newTime);
  }

  @Test
  public void getUsername() {
    Time t = new Time(time, username, id, rank);
    assertEquals(t.getUsername(), username);
  }

  @Test
  public void setUsername() {
    String newUserName = "newUserName";
    Time t = new Time(time, username, id, rank);
    t.setUsername(newUserName);
    assertEquals(t.getUsername(), newUserName);
  }

}