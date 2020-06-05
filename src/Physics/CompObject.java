package Physics;

import AI.Coordinates;
import AI.ShortestPath;
import java.util.ArrayList;
import java.util.Random;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CompObject {

  private Rectangle rectangle;
  private Polyline path;
  private Random rand;
  private PathTransition pathTransition;
  private ShortestPath shortestpath;
  private ArrayList<Coordinates> nodes;
  private ArrayList<Coordinates> open;
  private ArrayList<Coordinates> closed;
  private ArrayList<Coordinates> route;
  private ArrayList<Coordinates> shortpath;
  private Coordinates start;
  private Coordinates finish;
  private Double[] follow;
  private int[] pathInput;
  private Rectangle[] trackRect;
  /**
   * Constructor called when creating a computer object Initializes the objects image, size and
   * rotation Finds the shortest path for the object given the track and assigns the path to the
   * object
   *
   * @param pathInput start and finish coordinates
   * @param trackRect all available track pieces to mvoe on
   */
  public CompObject(int[] pathInput, Rectangle[] trackRect) {
    this.pathInput = pathInput;
    this.trackRect = trackRect;
    this.rand = new Random();

    // Creates rectangle which is the current computer car image
    this.rectangle = new Rectangle(25, 25);
    String image = CompObject.class.getResource("/Resources/Cars/CarYellow.png").toExternalForm();
    Image carimage = new Image(image);
    ImagePattern carim = new ImagePattern(carimage);
    this.rectangle.setFill(carim);
    Rotate rotate = new Rotate();
    rotate.setAngle(90);
    this.rectangle.getTransforms().add(rotate);
    this.shortpath = new ArrayList<Coordinates>();

    createShortestPath();

    createPath();

  }
  /**
   * Constructor called when creating a computer object Initializes the objects size Finds the
   * shortest path for the object given the track and assigns the path to the object
   */
  public CompObject() {
    this.pathInput = new int[]{};
    this.trackRect = new Rectangle[]{new Rectangle(1, 1, 1, 1)};
    this.rand = new Random();

    // Creates rectangle which is the current computer car image
    this.rectangle = new Rectangle(25, 25);
    this.shortpath = new ArrayList<Coordinates>();

    createShortestPath();

    createPath();

  }

  public PathTransition getPathTransition() {
    return pathTransition;
  }

  public Rectangle getRectangle() {
    return rectangle;
  }

  /**
   * Finds the shortest path using the astar algorithm
   */
  public void createShortestPath() {
    for (int i = 0; i < pathInput.length; i++) {
      ArrayList<Coordinates> nodes = new ArrayList<Coordinates>();
      ArrayList<Coordinates> open = new ArrayList<Coordinates>();
      ArrayList<Coordinates> closed = new ArrayList<Coordinates>();
      ArrayList<Coordinates> route = new ArrayList<Coordinates>();
      Coordinates start = new Coordinates(pathInput[i], pathInput[i + 1]);
      i += 2;
      Coordinates finish = new Coordinates(pathInput[i], pathInput[i + 1]);
      shortestpath = new ShortestPath(start, finish, nodes, open, closed, route, trackRect);
      for (int k = 0; k < shortestpath.route.size(); k++) {
        shortpath.add(shortestpath.route.get(k));
      }
      i++;
    }
  }

  /**
   * Assigns the path from the astar algorithm to the computer object and sets a random speed for
   * the computer car
   */
  public void createPath() {
    this.follow = new Double[(shortpath.size() * 2)];
    int count = 0;
    for (int k = 0; k < (shortpath.size() * 2); k += 2) {
      follow[k] = new Double(shortpath.get(count).getX());
      follow[k + 1] = new Double(shortpath.get(count).getY());
      count++;
    }

    // Creates the path that the computer car will follow and the settings for it
    this.path = new Polyline();
    this.path.getPoints().addAll(follow);
    this.pathTransition = new PathTransition();
    int minimum = 30;
    int maximum = 40;
    int duration = rand.nextInt((maximum - minimum) + 1) + minimum;
    this.pathTransition.setDuration(Duration.seconds(duration));
    this.pathTransition.setNode(rectangle);
    this.pathTransition.setPath(path);
    this.pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
    this.pathTransition.setCycleCount(3);
    this.pathTransition.setAutoReverse(false);
    this.pathTransition.play();
  }


}
