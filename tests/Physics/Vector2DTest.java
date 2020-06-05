package Physics;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Vector2DTest {

  @Test
  public void testMultFloat() {
    Vector2D test = new Vector2D(1, 1);
    Vector2D control = new Vector2D(2, 2);
    test = test.Mult(2);
    boolean same = false;
    if ((test.x == control.x) && (test.y == control.y)) {
      same = true;
    }
    assertTrue(same);
  }

  @Test
  public final void testMultVector2D() {
    Vector2D test = new Vector2D(1, 1);
    Vector2D control = new Vector2D(2, 2);
    test = test.Mult(control);
    boolean same = false;
    if ((test.x == control.x) && (test.y == control.y)) {
      same = true;
    }
    assertTrue(same);
  }

  @Test
  public final void testDiv() {
    Vector2D test = new Vector2D(2, 2);
    Vector2D control = new Vector2D(1, 1);
    test = test.Div(2);
    boolean same = false;
    if ((test.x == control.x) && (test.y == control.y)) {
      same = true;
    }
    assertTrue(same);
  }

  @Test
  public final void testAdd() {
    Vector2D test = new Vector2D(1, 1);
    Vector2D control = new Vector2D(2, 2);
    test = test.Add(test);
    boolean same = false;
    if ((test.x == control.x) && (test.y == control.y)) {
      same = true;
    }
    assertTrue(same);
  }

  @Test
  public final void testMin() {
    Vector2D test = new Vector2D(1, 1);
    Vector2D control = new Vector2D(2, 2);
    control = control.Min(test);
    boolean same = false;
    if ((test.x == control.x) && (test.y == control.y)) {
      same = true;
    }
    assertTrue(same);
  }

  @Test
  public final void testNormalize() {
    Vector2D control = new Vector2D(0.6f, 0.8f);
    Vector2D test = new Vector2D(3, 4);
    test = test.Normalize();
    boolean same = false;
    if ((test.x == control.x) && (test.y == control.y)) {
      same = true;
    }
    assertTrue(same);
  }

}
