package Database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {

  private String userName = "username";
  private String password = "password";
  private String email = "email";
  private int ID = 100;

  @Test
  public void username() {
    Player p = new Player(ID, userName, email, password);
    assertEquals(p.getUserName(), userName);
  }

  @Test
  public void setUserName() {
    String newUserName = "newUserName";
    Player p = new Player(ID, userName, email, password);
    p.setUserName(newUserName);
    assertEquals(p.getUserName(), newUserName);
  }

  @Test
  public void getEmail() {
    Player p = new Player(ID, userName, email, password);
    assertEquals(p.getEmail(), email);
  }

  @Test
  public void setEmail() {
    String newEmail = "newEmail";
    Player p = new Player(ID, userName, email, password);
    p.setEmail(newEmail);
    assertEquals(p.getEmail(), newEmail);
  }

  @Test
  public void getPassword() {
    Player p = new Player(ID, userName, email, password);
    assertEquals(p.getPassword(), password);
  }

  @Test
  public void setPassword() {
    String newPassworde = "newPassword";
    Player p = new Player(ID, userName, email, password);
    p.setPassword(newPassworde);
    assertEquals(p.getPassword(), newPassworde);
  }

  @Test
  public void getID() {
    Player p = new Player(ID, userName, email, password);
    assertEquals(p.getID(), ID);
  }

  @Test
  public void setID() {
    int newID = 10;
    Player p = new Player(ID, userName, email, password);
    p.setID(newID);
    assertEquals(p.getID(), newID);
  }
}