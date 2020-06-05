package Physics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Collider implements Serializable {

  public static List<Collider> activeColliders = new ArrayList<>();
  public Vector2D position;
  public Vector2D size;
  public GameObject gO;
  public Layers layer;
  public boolean inFinishLine = false;
  private boolean oilCollision = false;
  private long oilEndTime = 0;
  private boolean starCollision = false;
  private long starEndTime = 0;
  private float maxVelocity = 3;
  private Vector2D velocity = Vector2D.ZERO;

  /**
   * @param position - The position of the collider
   * @param size - The size of the collider
   * @param layer - The layer of the collider
   * @param gO - Which GameObject it is
   */
  public Collider(Vector2D position, Vector2D size, Layers layer, GameObject gO) {
    this.position = position;
    this.size = size;
    this.layer = layer;
    this.gO = gO;
    activeColliders.add(this);
  }

  /**
   * The class that is used to make objects collidable
   *
   * @return The maximum velocity value
   */
  public float getMaxVelocity() {
    return maxVelocity;
  }

  /**
   * @param maxVelocity - The value assigned as the maximum velocity
   */
  public void setMaxVelocity(float maxVelocity) {
    this.maxVelocity = maxVelocity;
  }

  /**
   * @return The velocity at that moment
   */
  public Vector2D getVelocity() {
    return velocity;
  }

  /**
   * @param velocity - The vector value of a velocity
   */
  public void setVelocity(Vector2D velocity) {
    this.velocity = velocity;
  }

  /**
   * @param targetLayer - The layer which is being checked to see if there have been any collisions
   * @return The list of objects that have collided with the target layer
   */
  public List<Collider> checkCollisions(Layers targetLayer) {
    List<Collider> collisions = new ArrayList<>();

    for (Collider collider : activeColliders) {
      if (collider.equals(this) || collider.layer != targetLayer) {
        continue;
      }
      if (collider.position.x < position.x + size.x
          && collider.position.x + collider.size.x > position.x
          && collider.position.y < position.y + size.y
          && collider.position.y + collider.size.y > position.y) {
        collisions.add(collider);
      }
    }

    return collisions;
  }

  /**
   * @param Velocity - The vector added to an objects position to see if there have been any
   * collisions
   * @return The final position vector
   */
  public Vector2D move(Vector2D Velocity) {
    for (int i = 0; i < Velocity.length; i++) {
      position = position.Add(Velocity);

      inFinishLine = checkCollisions(Layers.FINISH_LINE).size() > 0;

      if (checkCollisions(Layers.WALL).size() > 0) {
        position = position.Min(Velocity);
        gO.setVelocity(gO.getVelocity().Mult((float) -0.30));
        break;
      }

      if (oilCollision == true) {
        if (System.currentTimeMillis() >= oilEndTime) {
          maxVelocity = 4;
          oilCollision = false;
        }
      } else {
        if (checkCollisions(Layers.OIL).size() > 0) {
          oilCollision = true;
          maxVelocity = 1;
          if (velocity.x > 0) {
            velocity.x = 1;
          } else {
            velocity.y = 1;
          }

          long startTime = System.currentTimeMillis();
          oilEndTime = startTime + 4000;
        }
      }

      if (starCollision == true) {
        if (System.currentTimeMillis() >= starEndTime) {
          maxVelocity = 4;
          starCollision = false;
        }
      } else {
        if (checkCollisions(Layers.STAR).size() > 0) {
          starCollision = true;
          maxVelocity = 5;
          long startTime = System.currentTimeMillis();
          starEndTime = startTime + 4000;
        }
      }

    }
    return position;
  }


  /**
   * @param position - Sets the position of the collider
   */
  public void setPosition(Vector2D position) {
    this.position = position;
  }

  /**
   * @author Tufail
   */
  public enum Layers {
    CAR, WALL, FLOOR, FINISH_LINE, OIL, STAR
  }

}
