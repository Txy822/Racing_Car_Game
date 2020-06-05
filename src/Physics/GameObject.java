package Physics;// package Physics;

import GameModes.Multiplayer.GameModes.MultiplayerGame;
import Physics.Collider.Layers;
import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle implements Serializable {

  private Vector2D position;
  private Collider collider;
  private Rectangle image;

  /**
   * The class where the objects that are seen on the screen are made
   *
   * @param position - The position of the object
   * @param velocity - The velocity of the object
   * @param size - The size of the object
   * @param colliderLayer - The collider layer of the object
   */
  public GameObject(Vector2D position, Vector2D velocity, Vector2D size, Layers colliderLayer) {
    super(size.x, size.y);
    super.setX(position.x);
    super.setY(position.y);

    this.position = position;
    this.collider = new Collider(position, size, colliderLayer, this);
    collider.setVelocity(velocity);
    this.image = new Rectangle(this.position.x, this.position.y, size.x, size.y);

    String imageName =
        MultiplayerGame.class.getResource("/Resources/Cars/CarBlue.png").toExternalForm();
    Image image = new Image(imageName);
    ImagePattern car = new ImagePattern(image);

    this.image.setFill(car);
    this.image.setRotate(270);
  }

  /**
   * @param position - The position of the object
   * @param velocity - The velocity of the object
   * @param size - The size of the object
   */
  public GameObject(Vector2D position, Vector2D velocity, Vector2D size) {
    super(size.x, size.y);
    super.setX(position.x);
    super.setY(position.y);

    this.position = position;
    this.collider = new Collider(position, size, Layers.CAR, this);
    collider.setVelocity(velocity);
    this.image = new Rectangle(this.position.x, this.position.y, size.x, size.y);

  }

  /**
   * @param delta - The delta between updates
   */
  public void Update(float delta) {
    Vector2D tempVel = new Vector2D(collider.getVelocity().x, collider.getVelocity().y);
    tempVel.x *= delta;
    tempVel.y *= delta;

    position = collider.move(tempVel.Mult(10));
    image.setX(position.x);
    image.setY(position.y);


  }

  /**
   * @return Boolean value of whether it is in the finish line or not
   */
  public boolean inFinishLine() {
    return collider.inFinishLine;
  }

  /**
   * @return The current position of the object
   */
  public Vector2D getPosition() {
    return position;
  }

  /**
   * @param position - The position the object is being set to
   */
  public void setPosition(Vector2D position) {
    this.position = position;
    collider.setPosition(position);
  }

  /**
   * @param x - The x value that the position the object is being set to
   */
  public void setX(int x) {
    position.x = x;
    setPosition(position);
  }

  /**
   * @param y - The y value that the position the object is being set to
   */
  public void setY(int y) {
    position.y = y;
    setPosition(position);
  }

  /**
   * @return The current velocity
   */
  public Vector2D getVelocity() {
    return collider.getVelocity();
  }

  /**
   * @param velocity - The vector that the velocity is being set to
   */
  public void setVelocity(Vector2D velocity) {
    collider.setVelocity(velocity);
  }

  public Rectangle getImage() {
    return image;
  }

  public Collider getCollider() {
    return collider;
  }

  public void setCollider(Collider collider) {
    this.collider = collider;
  }

  public float getMaxVelocity() {
    return collider.getMaxVelocity();
  }

  public void setMaxVelocity(float maxVelocity) {
    collider.setMaxVelocity(maxVelocity);
  }

  public void setCarRotation(String direction) {
    if (direction == "Up") {
      this.image.setRotate(180);
    } else if (direction == "Down") {
      this.image.setRotate(0);
    } else if (direction == "Right") {
      this.image.setRotate(270);
    } else if (direction == "Left") {
      this.image.setRotate(90);
    }
  }

}
