package Server.Request;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class InitRequestTest {

  private InitRequest request;

  @Test
  public void handleRequest() {
    assertNotNull(request.handleRequest());
  }

  @Before
  public void setUp() {
    request = new InitRequest();
  }
}