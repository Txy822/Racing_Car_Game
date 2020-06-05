package Database;

public class Player {

  private String userName;
  private String password;
  private String email;

  private int ID;

  public Player(int playerID, String userName, String email, String password) {
    this.ID = playerID;
    this.userName = userName;
    this.password = password;
    this.email = email;
  }

  public Player() {
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getID() {
    return ID;
  }

  public void setID(int playerID) {
    this.ID = playerID;
  }
}
