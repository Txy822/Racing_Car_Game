package Database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class DatabaseControlTest {

  ArrayList<Integer> tracks = new ArrayList<>();
  private DatabaseControl dbContol;
  private String userName = "TestUsername12345";
  private String password = "1234";
  private int time = 64;
  private int rank = 1;
  private int trackID = 1;
  private int ID = 100001;

  @Before
  public void setUp() {
    dbContol = new DatabaseControl();

    tracks.add(1);
  }

  @Test
  public void createConnection() {
    assertNotNull(dbContol.createConnection());
  }

  @Test
  public void insertPlayerDetail() throws SQLException {
    String playerDetailTableName = "player_detail";
    int rowsBefore = dbContol.countRow(playerDetailTableName);
    String email = "1@gmail.com";
    dbContol.insertPlayerDetail(userName, email, password);
    int rowsAfter = dbContol.countRow(playerDetailTableName);
    int id = dbContol.getPlayerDetail(userName).getID();

    assertEquals(dbContol.getPlayerDetail(userName).getUserName(), userName);
    assertTrue(dbContol.isRegistered(id, userName, password));
    assertTrue(dbContol.isPlayerUserNameExist(userName));
    assertTrue(dbContol.isPlayerEmailExist(email));
    assertTrue(dbContol.isPasswordExist(id, password));
    assertTrue(dbContol.isRegistered(id, userName, password));

    assertEquals(rowsAfter - 1, rowsBefore);

    dbContol.deletePlayerDetail(id);


  }

  @Test
  public void updatePlayerDetail() {
    String uName = "username123";
    String pass = "1234900";
    String uEmail = "1@gmail1234.com";
    dbContol.insertPlayerDetail(uName, uEmail, pass);
    int id = dbContol.getPlayerDetail(uName).getID();
    String newPassword = "OneLove";
    dbContol.updatePlayerDetail(id, uName, uEmail, newPassword);
    Player expectedPlayer = dbContol.getPlayerDetail(uName);
    assertEquals(expectedPlayer.getPassword(), newPassword);

    dbContol.deletePlayerDetail(id);

  }

  @Test
  public void getPlayers() {

    assertNotNull(dbContol.getPlayers());

  }

  @Test
  public void getPlayerDetail() {

    assertNotNull(dbContol.getPlayerDetail(userName));
  }

  @Test
  public void countRow() throws SQLException {
    String leaderBoardTableName = "leader_board";
    dbContol.insertTimingToLeaderBoard(ID, time, trackID, rank);
    assertTrue(dbContol.countRow(leaderBoardTableName) > 0);
    dbContol.deleteLeaderBoardTime(ID);
  }

  @Test
  public void insertTimingToLeaderBoard() throws SQLException {
    String leaderBoardTableName = "leader_board";
    int rowsBefore = dbContol.countRow(leaderBoardTableName);
    dbContol.insertTimingToLeaderBoard(ID, time, trackID, rank);
    int rowsAfter = dbContol.countRow(leaderBoardTableName);
    assertEquals(rowsAfter - 1, rowsBefore);
    dbContol.deleteLeaderBoardTime(ID);

  }

  @Test
  public void updateLeaderBoard() {
    dbContol.insertTimingToLeaderBoard(ID, time, trackID, rank);
    dbContol.updateLeaderBoard(ID, time + 1, trackID);
    int expectedTime = dbContol.getTimeFromLeaderBoard(ID);
    assertEquals(expectedTime, time + 1);

    dbContol.deleteLeaderBoardTime(ID);
  }

  @Test
  public void getLeaderBoard() {

    assertNotNull(dbContol.getLeaderBoard(tracks));

  }

  @Test
  public void getPlayersTime() {
    assertNotNull(dbContol.getPlayersTime(trackID));
  }

  @Test
  public void getTimeFromLeaderBoard() {
    dbContol.insertTimingToLeaderBoard(ID, time, trackID, rank);
    assertTrue(dbContol.getTimeFromLeaderBoard(ID) > 0);
    dbContol.deleteLeaderBoardTime(ID);
  }

  @Test
  public void getTime() {
    dbContol.insertPlayersTime(ID, time, trackID);
    assertTrue(dbContol.getTime(ID) > 0);
    dbContol.deletePlayerTime(ID);

  }

  @Test
  public void bestTime() {
    int minimumTime = 0;
    String uName = "username11";
    String pass = "123445";
    String uEmail = "1@gmai11.com";
    dbContol.insertPlayerDetail(uName, uEmail, pass);
    int id = dbContol.getPlayerDetail(uName).getID();
    dbContol.insertPlayersTime(id, minimumTime, trackID);
    int bestTime = dbContol.bestTime(trackID).getTime();
    assertEquals(bestTime, minimumTime);
    dbContol.deletePlayerTime(id);
    dbContol.deletePlayerDetail(id);
  }

  @Test
  public void insertPlayersTime() throws SQLException {
    String playersTimeTableName = "players_time";
    int rowsBefore = dbContol.countRow(playersTimeTableName);
    dbContol.insertPlayersTime(ID, time, trackID);
    int rowsAfter = dbContol.countRow(playersTimeTableName);
    assertEquals(rowsAfter - 1, rowsBefore);

    dbContol.deletePlayerTime(ID);

  }

  @Test
  public void updatePlayersTime() {

    dbContol.insertPlayersTime(ID, time, trackID);
    dbContol.updatePlayersTime(ID, time + 1, trackID);
    int expectedTime = dbContol.getTime(ID);
    assertEquals(expectedTime, time + 1);

    dbContol.deletePlayerTime(ID);
  }

}