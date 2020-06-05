package Server.Response;

import static org.junit.Assert.assertEquals;

import Server.Player;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class GetPlayerPositionsResponseTest {

  private GetPlayerPositionsResponse response;
  private ArrayList<Player> players;

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void getPositions() {
    assertEquals(response.getPositions(), players);
  }

  @Before
  public void setUp() {
    players = new ArrayList<>();
    players.add(new Player("Player", 0, null, "Red"));

    response = new GetPlayerPositionsResponse(0, players);
  }

}