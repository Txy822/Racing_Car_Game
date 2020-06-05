package RandomTrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PointTest {

  @Test
  public void getX() {
    Point point1 = new Point(2, 3);
    assertEquals(2, point1.getX());
    Point point2 = new Point(-2, 3);
    assertEquals(-2, point2.getX());
  }

  @Test
  public void setX() {
    Point point1 = new Point(0, 0);
    point1.setX(5);
    assertEquals(5, point1.getX());
  }

  @Test
  public void getY() {
    Point point1 = new Point(2, 3);
    assertEquals(3, point1.getY());
    Point point2 = new Point(-2, -3);
    assertEquals(-3, point2.getY());
  }

  @Test
  public void setY() {
    Point point1 = new Point(0, 0);
    point1.setY(5);
    assertEquals(5, point1.getY());
  }

  @Test
  public void testHashCode() {
    Point point1 = new Point(7, 9);
    Point point2 = new Point(7, 9);
    Point point3 = new Point(-7, -9);
    assertEquals(point1.hashCode(), point2.hashCode());
    assertNotEquals(point1.hashCode(), point3.hashCode());
  }

  @Test
  public void testEquals() {
    Point point1 = new Point(7, 9);
    Point point2 = new Point(7, 9);
    Point point3 = new Point(-7, -9);
    assertTrue(point1.equals(point2));
    assertFalse(point1.equals(point3));
    assertFalse(point3.equals(point2));
  }
}