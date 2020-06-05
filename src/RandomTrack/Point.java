package RandomTrack;


public class Point {

  private int x;
  private int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get X
   *
   * @return value of X
   */
  public int getX() {
    return x;
  }

  /**
   * Set X
   *
   * @param x value of X
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Get Y
   *
   * @return value of Y
   */
  public int getY() {
    return y;
  }

  /**
   * Set y
   *
   * @param y value of y
   */
  public void setY(int y) {
    this.y = y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    Point other = (Point) obj;
    if (x != other.x) {
      return false;
    }

    if (y != other.y) {
      return false;
    }

    return true;
  }


}
