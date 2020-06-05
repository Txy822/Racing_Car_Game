package Server.Response;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class GetLeaderboardResponseTest {

  private GetLeaderboardResponse response;
  private Database.Time time;

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void getLeaderboard() {
    assertEquals(response.getLeaderboard().get(0), time);
  }

  @Before
  public void setUp() {
    ArrayList<Database.Time> leaderboard = new ArrayList<>();
    time = new Database.Time(1, "test", 1, 1);
    leaderboard.add(time);

    response = new GetLeaderboardResponse(0, leaderboard);
  }
}