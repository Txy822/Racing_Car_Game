package Physics;

import static org.junit.Assert.assertEquals;

import Physics.Collider.Layers;
import org.junit.Test;

public class ColliderTest {

  @Test
  public void testSetPosition() {
    GameObject gO = null;
    Collider testCollider =
        new Collider(new Vector2D(50, 50), new Vector2D(25, 25), Layers.WALL, gO);
    Vector2D position = new Vector2D(100, 100);
    testCollider.setPosition(position);
    assertEquals(position, testCollider.position);
  }

}
