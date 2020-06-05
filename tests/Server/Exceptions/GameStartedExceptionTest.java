package Server.Exceptions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;

public class GameStartedExceptionTest {

  @Before
  public void setUp() throws Exception {
    GameStartedException e = new GameStartedException("Test");
    assertEquals(e.getMessage(), "Test");
  }
}