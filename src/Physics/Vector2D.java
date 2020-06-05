package Physics;

import java.io.Serializable;

public class Vector2D implements Serializable {

  public static Vector2D ZERO = new Vector2D(0, 0);
  // Might need to swap signs on UP and DOWN
  public static Vector2D UP = new Vector2D(0, 1);
  public static Vector2D DOWN = new Vector2D(0, -1);
  public static Vector2D LEFT = new Vector2D(-1, 0);
  public static Vector2D RIGHT = new Vector2D(1, 0);
  public float x, y;
  public float length;

  /**
   * The constructor for the Vector class
   *
   * @param x - Starting x value
   * @param y - Starting y value
   */
  public Vector2D(float x, float y) {
    this.x = x;
    this.y = y;
    this.length = (float) Math.sqrt(x * x + y * y);
  }

  /**
   * @param mult - The value that the vector is multiplied by
   * @return The vector after it has been multiplied
   */
  public Vector2D Mult(float mult) {
    x *= mult;
    y *= mult;

    return this;
  }

  /**
   * @param vector - The vector that this vector is multiplied by
   * @return The vector after it has been multiplied
   */
  public Vector2D Mult(Vector2D vector) {
    x *= vector.x;
    y *= vector.y;

    return this;
  }

  /**
   * @param div - The value that the vector is divided by
   * @return The vector after it has been divided
   */
  public Vector2D Div(float div) {
    x /= div;
    y /= div;

    return this;
  }

  /**
   * @param add - The vector that is being added to this vector
   * @return The sum of the two vectors
   */
  public Vector2D Add(Vector2D add) {
    x += add.x;
    y += add.y;

    return this;
  }

  /**
   * @param min - The vector that this vector is being subtracted by
   * @return The result of this calculation
   */
  public Vector2D Min(Vector2D min) {
    x -= min.x;
    y -= min.y;

    return this;
  }

  /**
   * @return The normalised vector
   */
  public Vector2D Normalize() {
    return this.Div(length);
  }

}
