package GameModes.Tracks;

import java.io.Serializable;
import javafx.scene.shape.Rectangle;

public class Track implements Serializable {

  private int[][] layout;
  private int width;
  private int height;
  private Rectangle[] rectangles;
  private String name;
  private int[] AIroute;
  private Rectangle[] trackRectangles;

  public Track(int[][] layout, int width, int height, String name, int[] AIroute) {
    this.layout = layout;
    this.width = width;
    this.height = height;
    this.name = name;
    this.AIroute = AIroute;

    // Sets up the AI waypoints
    for (int i = 0; i < AIroute.length; i++) {
      if (i % 2 == 0) {
        this.AIroute[i] = (AIroute[i] * 100) + 50;
      } else {
        this.AIroute[i] = (AIroute[i] * 100) + 50;
      }

    }

    rectangles = new Rectangle[width * height];
  }

  public int[] getAIroute() {
    return AIroute;
  }

  public Rectangle[] getTrackRectangles() {
    return trackRectangles;
  }

  public void setTrackRectangles(Rectangle[] trackRectangles) {
    this.trackRectangles = trackRectangles;
  }

  public int[][] getLayout() {
    return layout;
  }

  public void setLayout(int[][] layout) {
    this.layout = layout;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Rectangle[] getRectangles() {
    return rectangles;
  }

  public void setRectangles(Rectangle[] rectangles) {
    this.rectangles = rectangles;
  }

  @Override
  public String toString() {
    return name;
  }
}
