package Server.Request;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CloseConnectionRequestTest {

  private CloseConnectionRequest request;

  @Test
  public void handleRequest() {
    assertEquals(request.handleRequest(), null);
  }

  @Before
  public void setUp() {
    request = new CloseConnectionRequest();
  }
}