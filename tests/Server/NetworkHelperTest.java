package Server;

import static org.junit.Assert.assertEquals;

import Server.Request.InitRequest;
import Server.Request.Request;
import org.junit.Test;

public class NetworkHelperTest {

  @Test
  public void implementsInterface() {
    assertEquals(NetworkHelper.implementsInterface(new InitRequest(), Request.class), true);
  }

}