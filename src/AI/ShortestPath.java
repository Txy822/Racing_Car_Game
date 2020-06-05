package AI;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class ShortestPath {

  public ArrayList<Coordinates> route;
  ArrayList<Coordinates> nodes;
  ArrayList<Coordinates> open;
  ArrayList<Coordinates> closed;
  Coordinates start;
  Coordinates finish;

  /**
   * Finds the shortest path using the astar algorithm
   *
   * @param inStart start coorindates
   * @param inFinish finish coordinates
   * @param innodes empty array to hold nodes that the path can travel through
   * @param inopen coordinates that the algorithm is currently looking at, the start coordinate is
   * added
   * @param inclosed empty array to hold the final path
   * @param inroute empty array to hold the final path
   * @param Coords a list of all the coordinates that are available to be travelled through as
   * rectangles
   */
  public ShortestPath(Coordinates inStart, Coordinates inFinish, ArrayList<Coordinates> innodes,
      ArrayList<Coordinates> inopen,
      ArrayList<Coordinates> inclosed, ArrayList<Coordinates> inroute, Rectangle[] Coords) {
    this.start = inStart;
    this.finish = inFinish;
    this.open = inopen;
    open.add(start);
    this.nodes = innodes;
    this.closed = inclosed;
    createNodes(Coords);
    this.route = inroute;
    this.route = astar(this.finish);

  }

  /**
   * Creates nodes available to travel through from the given rectangles
   */
  public void createNodes(Rectangle[] Coords) {

    for (int i = 0; i < Coords.length; i++) {
      this.nodes.add(new Coordinates((int) Coords[i].getX(), (int) Coords[i].getY()));
    }
  }

  /**
   * Uses the astar algorithm to find the shortest path from start to finish using heuristics
   *
   * @param finish finish coordinate
   * @return the shortest path from start to finish
   */
  public ArrayList<Coordinates> astar(Coordinates finish) {
    while (open.isEmpty() == false) {
      orderopen();

      Coordinates q = open.get(0);
      open.remove(0);
      closed.add(q);
      ArrayList<Coordinates> neighbours = new ArrayList<Coordinates>();
      getNeighbours(neighbours, q);

      int numberOfNeighbours = neighbours.size();
      for (int i = 0; i < numberOfNeighbours; i++) {

        Coordinates neighbour = neighbours.get(0);
        neighbours.remove(0);

        if ((neighbour.getX() == finish.getX()) && (neighbour.getY() == finish.getY())) {
          closed.add(neighbour);
          return closed;
        }

        boolean notClosed = false;
        for (Coordinates node : closed) {
          if ((node.getX() == neighbour.getX()) && (node.getY() == neighbour.getY())) {
            notClosed = true;
          }
        }

        boolean addOpen = true;
        if (notClosed == false) {
          neighbour.setg(q.getg() + Math.sqrt(Math.pow((neighbour.getX() - q.getX()), 2) + Math
              .pow((neighbour.getY() - q.getY()), 2)));
          neighbour.seth(Math.sqrt(Math.pow((finish.getX() - neighbour.getX()), 2) + Math
              .pow((finish.getY() - neighbour.getY()), 2)));
          neighbour.setf(neighbour.getg() + neighbour.geth());
          for (Coordinates node2 : open) {
            if ((node2.getX() == neighbour.getX()) && (node2.getY() == neighbour.getY())) {
              if (node2.getg() < neighbour.getg()) {
                addOpen = false;
                node2.setg(q.getg() + Math.sqrt(Math.pow((neighbour.getX() - q.getX()), 2) + Math
                    .pow((neighbour.getY() - q.getY()), 2)));
                node2.seth(Math.sqrt(Math.pow((finish.getX() - neighbour.getX()), 2) + Math
                    .pow((finish.getY() - neighbour.getY()), 2)));
                node2.setf(neighbour.getg() + neighbour.geth());
              }
            }
          }
        }

        if (addOpen == true && notClosed == false) {
          open.add(neighbour);
        }

      }

    }

    return closed;
  }

  /**
   * Order the list of nodes which the path can travel to, starting with the node with the shortest
   * predicted distance
   */
  public void orderopen() {

    for (int i = 0; i < (open.size() - 1); i++) {

      for (int k = 0; k < (open.size() - (i + 1)); k++) {

        if (open.get(k).getf() > open.get(k + 1).getf()) {
          Coordinates temp = new Coordinates(0, 0);
          Coordinates temp2 = new Coordinates(0, 0);
          temp = open.get(k);
          temp2 = open.get(k + 1);
          open.remove(k);
          open.add(k, temp2);
          open.remove(k + 1);
          open.add(k + 1, temp);

        }


      }
    }
  }

  /**
   * Finds all the neighbours next to the current node
   *
   * @param neighbours the empty list to hold the nieghbours
   * @param current the current node, to find the neighbours of
   * @return the list of all the neighbours to the current node
   */
  public ArrayList<Coordinates> getNeighbours(ArrayList<Coordinates> neighbours,
      Coordinates current) {

    Coordinates temp2 = new Coordinates(0, 0);
    temp2.setX(current.getX() - 100);
    temp2.setY(current.getY() - 0);
    for (Coordinates node : nodes) {
      if ((node.getX() == temp2.getX()) && (node.getY() == temp2.getY())) {
        neighbours.add(temp2);
      }
    }

    Coordinates temp4 = new Coordinates(0, 0);
    temp4.setX(current.getX() - 0);
    temp4.setY(current.getY() - 100);
    for (Coordinates node : nodes) {
      if ((node.getX() == temp4.getX()) && (node.getY() == temp4.getY())) {
        neighbours.add(temp4);
      }
    }

    Coordinates temp5 = new Coordinates(0, 0);
    temp5.setX(current.getX() - 0);
    temp5.setY(current.getY() + 100);
    for (Coordinates node : nodes) {
      if ((node.getX() == temp5.getX()) && (node.getY() == temp5.getY())) {
        neighbours.add(temp5);
      }
    }

    Coordinates temp7 = new Coordinates(0, 0);
    temp7.setX(current.getX() + 100);
    temp7.setY(current.getY() - 0);
    for (Coordinates node : nodes) {
      if ((node.getX() == temp7.getX()) && (node.getY() == temp7.getY())) {
        neighbours.add(temp7);
      }
    }

    return neighbours;
  }
}
