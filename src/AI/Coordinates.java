package AI;

public class Coordinates {

  private int xCoord;
  private int yCoord;
  private double f = 0;
  private double g = 0;
  private double h = 0;

  /**
   * Creates the Coordinates variable which is used for the shortest path
   *
   * @param x x value for the coordinate
   * @param y y value for the coordinate
   */
  public Coordinates(int x, int y) {
    this.xCoord = x;
    this.yCoord = y;
  }

  public void setf(double f) {
    this.f = f;
  }

  public void setg(double g) {
    this.g = g;
  }

  public void seth(double h) {
    this.h = h;
  }

  public int getX() {
    return this.xCoord;
  }

  public void setX(int x) {
    this.xCoord = x;
  }

  public int getY() {
    return this.yCoord;
  }

  public void setY(int y) {
    this.yCoord = y;
  }

  public double getf() {
    return this.f;
  }

  public double getg() {
    return this.g;
  }

  public double geth() {
    return this.h;
  }

}
