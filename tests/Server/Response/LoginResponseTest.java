package Server.Response;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LoginResponseTest {

  private LoginResponse response;

  @Test
  public void getCode() {
    assertEquals(response.getCode(), 0);
  }

  @Test
  public void isSuccessful() {
    assertEquals(response.isSuccessful(), true);
  }

  @Before
  public void setUp() {
    response = new LoginResponse(0, true);
  }
}