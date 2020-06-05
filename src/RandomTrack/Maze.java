package RandomTrack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;


public class Maze {

  public int width, height;
  public int[][] track;
  public Deque<Point> route = new ArrayDeque<>();
  private Deque<Point> visitStack = new ArrayDeque<>();
  private Deque<Point> originPoints = new ArrayDeque<>();
  private Deque<Point> solveStack = new ArrayDeque<>();

  /**
   * This method returns a 2D array of track
   *
   * @return 2D array of track.
   */
  public int[][] getmaze() {
    return track;
  }

  /**
   * This method will create a random track
   *
   * @param width The width of the random track.
   * @param height The height of the random track.
   * @return 1 if create the random track successfully.
   */
  public int createMaze(int width, int height) {
    int curX;
    int curY;
    int sign;
    Point solvecurPoint;

    this.width = width - 2;
    this.height = height - 2;

    int[][] map = new int[this.width][this.height];

    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        map[i][j] = 8;
      }
    }

    for (int i = 1; i < this.width; i = i + 2) {
      for (int j = 1; j < this.height; j = j + 2) {
        map[i][j] = 1;
        originPoints.add(new Point(i, j));
      }
    }

    Point curPoint = new Point(1, 1);
    originPoints.remove(curPoint);

    Random numberRandom = new Random();

    while (!curPoint.equals(new Point(this.width - 2, this.height - 2))) {
      switch (numberRandom.nextInt(4) + 1) {
        case 1:
          if (curPoint.getX() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX() - 2, curPoint.getY()))) {
            map[curPoint.getX() - 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() - 2, curPoint.getY());
            continue;
          }

          if (curPoint.getX() + 2 < this.width && originPoints
              .remove(new Point(curPoint.getX() + 2, curPoint.getY()))) {
            map[curPoint.getX() + 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() + 2, curPoint.getY());
            continue;
          }

          if (curPoint.getY() + 2 <= this.height && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() + 2))) {
            map[curPoint.getX()][curPoint.getY() + 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() + 2);
            continue;
          }

          if (curPoint.getY() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() - 2))) {
            map[curPoint.getX()][curPoint.getY() - 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() - 2);
          }

          curPoint = visitStack.pollFirst();

          if (curPoint == null) {
            break;
          }
          break;

        case 2:
          if (curPoint.getX() + 2 < this.width && originPoints
              .remove(new Point(curPoint.getX() + 2, curPoint.getY()))) {
            map[curPoint.getX() + 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() + 2, curPoint.getY());
            continue;
          }

          if (curPoint.getY() + 2 <= this.height && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() + 2))) {
            map[curPoint.getX()][curPoint.getY() + 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() + 2);
            continue;
          }

          if (curPoint.getY() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() - 2))) {
            map[curPoint.getX()][curPoint.getY() - 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() - 2);
          }

          if (curPoint.getX() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX() - 2, curPoint.getY()))) {
            map[curPoint.getX() - 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() - 2, curPoint.getY());
            continue;
          }

          curPoint = visitStack.pollFirst();
          if (curPoint == null) {
            break;
          }
          break;

        case 3:
          if (curPoint.getY() + 2 <= this.height && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() + 2))) {
            map[curPoint.getX()][curPoint.getY() + 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() + 2);
            continue;
          }

          if (curPoint.getY() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() - 2))) {
            map[curPoint.getX()][curPoint.getY() - 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() - 2);
          }

          if (curPoint.getX() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX() - 2, curPoint.getY()))) {
            map[curPoint.getX() - 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() - 2, curPoint.getY());
            continue;
          }

          if (curPoint.getX() + 2 < this.width && originPoints
              .remove(new Point(curPoint.getX() + 2, curPoint.getY()))) {
            map[curPoint.getX() + 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() + 2, curPoint.getY());
            continue;
          }

          curPoint = visitStack.pollFirst();
          if (curPoint == null) {
            break;
          }
          break;

        case 4:
          if (curPoint.getY() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() - 2))) {
            map[curPoint.getX()][curPoint.getY() - 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() - 2);
          }

          if (curPoint.getX() - 2 > 0 && originPoints
              .remove(new Point(curPoint.getX() - 2, curPoint.getY()))) {
            map[curPoint.getX() - 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() - 2, curPoint.getY());
            continue;
          }

          if (curPoint.getX() + 2 < this.width && originPoints
              .remove(new Point(curPoint.getX() + 2, curPoint.getY()))) {
            map[curPoint.getX() + 1][curPoint.getY()] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX() + 2, curPoint.getY());
            continue;
          }

          if (curPoint.getY() + 2 <= this.height && originPoints
              .remove(new Point(curPoint.getX(), curPoint.getY() + 2))) {
            map[curPoint.getX()][curPoint.getY() + 1] = 1;
            visitStack.addFirst(curPoint);
            curPoint = new Point(curPoint.getX(), curPoint.getY() + 2);
            continue;
          }

          curPoint = visitStack.pollFirst();
          if (curPoint == null) {
            break;
          }
          break;
      }
    }

    route.addFirst(new Point(1, 1));

    if (map[1][2] == 1) {
      solveStack.add(new Point(1, 2));
    }

    if (map[2][1] == 1) {
      solveStack.add(new Point(2, 1));
    }

    map[1][1] = 2;
    solvecurPoint = solveStack.pop();
    curX = solvecurPoint.getX();
    curY = solvecurPoint.getY();
    map[curX][curY] = 2;
    route.addFirst(new Point(curX, curY));
    while (curX != this.width - 2 || curY != this.height - 2) {
      sign = 0;

      if (map[curX][curY + 1] == 1) {
        solveStack.addFirst(new Point(curX, curY + 1));
        sign++;
      }

      if (map[curX - 1][curY] == 1) {
        solveStack.addFirst(new Point(curX - 1, curY));
        sign++;
      }

      if (map[curX][curY - 1] == 1) {
        solveStack.addFirst(new Point(curX, curY - 1));
        sign++;
      }

      if (map[curX + 1][curY] == 1) {
        solveStack.addFirst(new Point(curX + 1, curY));
        sign++;
      }

      if (sign == 0) {
        route.pop();
        solvecurPoint = route.getFirst();
        curX = solvecurPoint.getX();
        curY = solvecurPoint.getY();
        continue;
      }

      solvecurPoint = solveStack.pop();
      curX = solvecurPoint.getX();
      curY = solvecurPoint.getY();
      route.addFirst(new Point(curX, curY));
      map[curX][curY] = 2;
    }

    while (!route.isEmpty()) {
      solvecurPoint = route.pop();
      map[solvecurPoint.getX()][solvecurPoint.getY()] = 3;
    }

    int[][] circle = new int[width][height];

    for (int i = 0; i < this.width; i++) {
      for (int j = 0; j < this.height; j++) {
        circle[i + 2][j] = map[i][j];
      }
    }

    for (int i = 1; i < height - 1; i++) {
      circle[1][i] = 3;
    }
    for (int i = 1; i < width - 1; i++) {
      circle[i][height - 2] = 3;
    }

    circle[2][1] = 3;
    circle[width - 2][height - 3] = 3;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (circle[i][j] != 3) {
          circle[i][j] = 8;
        }
      }
    }

    circle[2][1] = 4;

    this.track = circle;

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (circle[i][j] == 3) {
          route.addFirst(new Point(i, j));
        }
      }
    }

    return 1;
  }
}
