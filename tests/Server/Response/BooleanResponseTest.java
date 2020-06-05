package Server.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BooleanResponseTest {

  private BooleanResponse response;

  @Before
  public void setUp() throws Exception {
    response = new BooleanResponse(true);
  }

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void isBool() {
    assertTrue(response.isBool());
  }

  @Test
  public void setBool() {
    response.setBool(false);
    assertFalse(response.isBool());
  }
}