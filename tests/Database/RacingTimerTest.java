package Database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RacingTimerTest {

  RacingTimer timer = new RacingTimer();

  @Test
  public void msToTime() {
    int duration = 10000;
    String actual = "0:0:10.0";
    String expected = timer.msToTime(duration);

    assertEquals(expected, actual);
  }

  @Test
  public void timeToms() {
    int actual = 10000;
    String time = "0:0:10:0";
    int expected = timer.timeToms(time);

    assertEquals(expected, actual);

  }

  @Test
  public void getTimer() {

  }

  @Test
  public void setTime() {
    timer.setTime(100);
    assertEquals(timer.getTime(), 100);
  }
}