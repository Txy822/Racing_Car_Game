package Physics;

import RandomTrack.Maze;
import RandomTrack.Point;
import javafx.scene.shape.Rectangle;

public class Tracks {

  int[][] track1;
  Integer width;
  Integer height;
  int map[][];
  Rectangle trackRectangles[];
  int[] startfinish;

  public Tracks() {

  }

  /**
   * This is the class that generates the initial track and random track
   *
   * @return The 2D-array track1
   */
  public int[][] getTrack1() {
    return track1;
  }

  /**
   * @return The 2D-array map
   */
  public int[][] getMap() {
    return map;
  }

  /**
   * @return An array of JavaFX rectangles
   */
  public Rectangle[] getTrackRectangles() {
    return trackRectangles;
  }

  /**
   * @return The array StartFinish
   */
  public int[] getStartfinish() {
    return startfinish;
  }

  /**
   * Creates the track as a set 2D array and sets the width, height and start and finish coordinates
   * for shortest path
   */
  public void track1() {
    int[][] initialtrack = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 2, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 4, 5, 5, 5, 5, 5, 5, 5, 5, 3, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},};

    this.track1 = initialtrack;
    this.width = 16;
    this.height = 9;
    this.trackRectangles = new Rectangle[(this.width * this.height)];
    this.startfinish = new int[]{3, 3, 10, 1, 10, 6, 1, 6, 1, 2, 3, 3};

  }

  /**
   * Creates a random track as a 2D array and sets the start and finish coordinates for shortest
   * path
   */
  public void randomtrack() {
    Maze maze = new Maze();
    this.width = 7;
    this.height = 13;
    maze.createMaze(this.width, this.height);
    this.map = maze.getmaze();
    this.trackRectangles = new Rectangle[(this.width * this.height)];
    this.startfinish = new int[maze.route.size() * 2];
    for (int i = 0; i < maze.route.size(); i += 2) {
      Point current = maze.route.pop();
      this.startfinish[i] = current.getX();
      this.startfinish[i + 1] = current.getY();
    }
  }

}
