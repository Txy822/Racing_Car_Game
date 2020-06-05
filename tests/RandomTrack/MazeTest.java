package RandomTrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MazeTest {

  private Maze maze = new Maze();

  @Test
  public void createMaze() {
    assertEquals(maze.createMaze(15, 11), 1);
  }

  @Test
  public void getmaze() {
    maze.createMaze(15, 11);
    assertNotNull(maze.getmaze());
  }

}