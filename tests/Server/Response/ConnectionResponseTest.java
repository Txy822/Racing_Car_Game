package Server.Response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConnectionResponseTest {

  private ConnectionResponse response;

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Before
  public void setUp() {
    response = new ConnectionResponse(0);
  }
}