package Server.Request;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import Database.DatabaseControl;
import org.junit.Before;
import org.junit.Test;

public class GetLeaderboardRequestTest {

  private GetLeaderboardRequest request;
  private DatabaseControl databaseControl;

  @Test
  public void handleRequest() {
    assertNotNull(request.handleRequest());
  }

  @Test
  public void setDatabase() {
    assertTrue(databaseControl == request.getDatabaseControl());
  }

  @Before
  public void setUp() {
    databaseControl = new DatabaseControl();
    request = new GetLeaderboardRequest(0);
    request.setDatabase(databaseControl);
  }
}