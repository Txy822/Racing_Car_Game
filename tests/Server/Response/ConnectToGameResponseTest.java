package Server.Response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ConnectToGameResponseTest {

  private ConnectToGameResponse response;

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void getPlayerId() {
    assertEquals(response.getPlayerId(), 1);
  }

  @Before
  public void setUp() {
    response = new ConnectToGameResponse(0, 1);
  }
}