package Server;

import static org.junit.Assert.assertEquals;

import Physics.Vector2D;
import org.junit.Test;

public class PlayerTest {

  @Test
  public void setVector() {
    ;
    Vector2D position = new Vector2D(0, 0);
    Player player = new Player("Player", 0, new Vector2D(5, 5), "Red");
    player.setVector(position);
    assertEquals(player.getVector(), position);
  }

  @Test
  public void getVector() {

    Vector2D position = new Vector2D(0, 0);
    Player player = new Player("Player", 0, position, "Red");
    assertEquals(player.getVector(), position);
  }

  @Test
  public void getId() {
    Player player = new Player("Player", 0, null, "Red");
    assertEquals(player.getId(), 0);
  }

  @Test
  public void setPlayerName() {
    Player player = new Player("Player", 0, null, "Red");
    player.setPlayerName("Empty");
    assertEquals(player.getPlayerName(), "Empty");
  }
}