package AI;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import org.junit.Test;

public class ShortestPathTest {

  ArrayList<Coordinates> nodes = new ArrayList<Coordinates>();
  ArrayList<Coordinates> open = new ArrayList<Coordinates>();
  ArrayList<Coordinates> closed = new ArrayList<Coordinates>();
  ArrayList<Coordinates> route = new ArrayList<Coordinates>();
  Coordinates start = new Coordinates(1, 1);
  Coordinates finish = new Coordinates(2, 2);
  Rectangle[] track = new Rectangle[]{};
  ShortestPath shortestpath = new ShortestPath(start, finish, nodes, open, closed, route, track);

  @Test
  public void createNodes() {
    shortestpath.createNodes(track);
  }

  @Test
  public void astar() {
    shortestpath.astar(finish);
  }

  @Test
  public void orderopen() {
    shortestpath.orderopen();
  }

  @Test
  public void getNeighbours() {
    ArrayList<Coordinates> neighbours = new ArrayList<Coordinates>();
    Coordinates current = new Coordinates(50, 50);
    assertNotNull(shortestpath.getNeighbours(neighbours, current));
  }
}