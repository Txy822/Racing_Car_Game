package Physics;

import static org.junit.Assert.assertEquals;

import Physics.Collider.Layers;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

public class GameObjectTest {

  @Test
  public void Update() {
  }

  @Test
  public final void testGetPosition() {
    Vector2D position = new Vector2D(0, 0);
    GameObject testObject = new GameObject(position, new Vector2D(0, 0), new Vector2D(25, 25));
    assertEquals(position, testObject.getPosition());
  }

  @Test
  public final void testSetPosition() {
    Vector2D position = new Vector2D(0, 0);
    GameObject testObject =
        new GameObject(new Vector2D(50, 50), new Vector2D(30, 30), new Vector2D(25, 25));
    testObject.setPosition(position);
    assertEquals(position, testObject.getPosition());
  }

  @Test
  public final void testSetXInt() {
    Vector2D position = new Vector2D(25, 0);
    GameObject testObject =
        new GameObject(new Vector2D(50, 50), new Vector2D(30, 30), new Vector2D(25, 25));
    testObject.setX((int) position.x);
    assertEquals(position.x, testObject.getPosition().x, 0);
  }

  @Test
  public final void testSetYInt() {
    Vector2D position = new Vector2D(25, 0);
    GameObject testObject =
        new GameObject(new Vector2D(50, 50), new Vector2D(30, 30), new Vector2D(25, 25));
    testObject.setY((int) position.y);
    assertEquals(position.y, testObject.getPosition().y, 0);
  }

  @Test
  public final void testGetVelocity() {
    Vector2D velocity = new Vector2D(10, 10);
    GameObject testObject = new GameObject(new Vector2D(50, 50), velocity, new Vector2D(25, 25));
    assertEquals(velocity, testObject.getVelocity());
  }

  @Test
  public final void testSetVelocity() {
    Vector2D velocity = new Vector2D(10, 10);
    GameObject testObject =
        new GameObject(new Vector2D(50, 50), new Vector2D(30, 30), new Vector2D(25, 25));
    testObject.setVelocity(velocity);
    assertEquals(velocity, testObject.getVelocity());
  }

  @Test
  public final void testGetImage() {
    GameObject testObject =
        new GameObject(new Vector2D(25, 25), new Vector2D(0, 0), new Vector2D(50, 50));
    Rectangle testImage = testObject.getImage();
    assertEquals(testImage, testObject.getImage());
  }

  @Test
  public final void testGetCollider() {
    GameObject testObject =
        new GameObject(new Vector2D(25, 25), new Vector2D(0, 0), new Vector2D(50, 50));
    Collider testCollider = testObject.getCollider();
    assertEquals(testCollider, testObject.getCollider());
  }

  @Test
  public final void testSetCollider() {
    GameObject testObject =
        new GameObject(new Vector2D(25, 25), new Vector2D(0, 0), new Vector2D(50, 50));
    Collider testCollider =
        new Collider(new Vector2D(50, 50), new Vector2D(50, 50), Layers.WALL, testObject);
    testObject.setCollider(testCollider);
    assertEquals(testCollider, testObject.getCollider());
  }

}
