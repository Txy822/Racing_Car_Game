package Physics;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CompObjectTest {

  private CompObject AIcar = new CompObject();

  @Test
  public void getRectangle() {
    assertNotNull(AIcar.getRectangle());
  }

  @Test
  public void getPathTransition() {
    assertNotNull(AIcar.getPathTransition());
  }

  @Test
  public void createShortestPath() {
    AIcar.createShortestPath();
  }

  @Test
  public void createPath() {
    AIcar.createPath();
  }
}