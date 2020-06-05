package Database;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {

  int id = 0;
  private DatabaseControl dbContol;
  private String userName = "uniqueName";
  private String password = "12345";
  private String email = "1@gmail.com";

  @Before
  public void setUp() throws Exception {
    dbContol = new DatabaseControl();
  }

  @After
  public void tearDown() throws Exception {
    dbContol.deletePlayerDetail(id);
  }

  @Test
  public void start() {
    dbContol.insertPlayerDetail(userName, email, password);
    int id = dbContol.getPlayerDetail(userName).getID();

    assertTrue(dbContol.isRegistered(id, userName, password));
    dbContol.deletePlayerDetail(id);


  }

}