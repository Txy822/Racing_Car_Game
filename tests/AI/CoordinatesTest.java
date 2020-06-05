package AI;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CoordinatesTest {

  private int x = 1;
  private int y = 1;
  private int f = 1;
  private int h = 1;
  private int g = 1;
  private Coordinates coordinates = new Coordinates(1, 1);

  @Test
  public void setX() {
    coordinates.setX(x);
  }

  @Test
  public void setY() {
    coordinates.setY(y);
  }

  @Test
  public void setf() {
    coordinates.setf(f);
  }

  @Test
  public void setg() {
    coordinates.setg(g);
  }

  @Test
  public void seth() {
    coordinates.setg(g);
  }

  @Test
  public void getX() {
    assertEquals(coordinates.getX(), 1);
  }

  @Test
  public void getY() {
    assertEquals(coordinates.getY(), 1);
  }

  @Test
  public void getf() {
    assertEquals(coordinates.getf(), 1, 1);
  }

  @Test
  public void getg() {
    assertEquals(coordinates.getg(), 1, 1);
  }

  @Test
  public void geth() {
    assertEquals(coordinates.geth(), 1, 1);
  }

}