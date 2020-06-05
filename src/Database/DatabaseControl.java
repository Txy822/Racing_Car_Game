package Database;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * A class to manage the Racing_game_database tables
 */
public class DatabaseControl {

  private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/racing_game_database?useSSL=false&serverTimezone=GMT";
  private static final String DATABASE_USERNAME = "root";
  private static final String DATABASE_PASSWORD = "12345";

  /**
   * Creates connection or open connection
   *
   * @return A data type of Connection
   */
  public Connection createConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return connection;
  }

  /**
   * To insert the player detail to player_detail table
   *
   * @param playerUserName A variable of type String
   * @param playerEmail A variable of type String
   * @param playerPassword A variable of type String
   */
  public void insertPlayerDetail(String playerUserName, String playerEmail, String playerPassword) {
    String insertQuery = "INSERT INTO player_detail(user_name,email,password) VALUES(?,?,?);";
    Connection connection;
    PreparedStatement preparedStatement;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setString(1, playerUserName);
      preparedStatement.setString(2, playerEmail);
      preparedStatement.setString(3, playerPassword);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  /**
   * Updates the player detail table
   *
   * @param playerID player ID
   * @param user_name player user name
   * @param email player email
   * @param password player password
   */
  public void updatePlayerDetail(int playerID, String user_name, String email, String password) {
    Connection connection;
    PreparedStatement preparedStatement;
    String updatePlayerDetail = "UPDATE player_detail SET user_name =?,email=?,password=? WHERE id= ?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(updatePlayerDetail);
      preparedStatement.setString(1, user_name);
      preparedStatement.setString(2, email);
      preparedStatement.setString(3, password);
      preparedStatement.setInt(4, playerID);
      preparedStatement.executeUpdate();

      preparedStatement.close();
      connection.close();

    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  /**
   * Fetches the player Detail
   *
   * @return List of players, ArrayList data type
   */
  public ArrayList<Player> getPlayers() {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Player player;
    String fetchQuery = "SELECT id,user_name,email, password FROM player_detail";
    ArrayList<Player> arrayList = new ArrayList<>();
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(fetchQuery);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int ID = resultSet.getInt("id");
        String uname = resultSet.getString("user_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        player = new Player(ID, uname, email, password);
        arrayList.add(player);
      }
      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
    }
    return arrayList;
  }

  /**
   * Gets player's detail
   *
   * @param userName A variable of type String
   * @return Player data type
   */
  public Player getPlayerDetail(String userName) {
    Connection connection;

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Player player = new Player();
    String detailQuery = "SELECT * FROM player_detail WHERE user_name= ?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(detailQuery);
      preparedStatement.setString(1, userName);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {

        int ID = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        player = new Player(ID, userName, email, password);

      }

      preparedStatement.close();
      resultSet.close();
      connection.close();
      return player;
    } catch (SQLException ex) {
      printSQLException(ex);
      return null;
    }
  }

  /**
   * Checks the registration of player according to the user name and the password
   *
   * @param ID user ID
   * @param username A variable of type String
   * @param password A variable of type String
   * @return Boolean data type
   */
  public boolean isRegistered(int ID, String username, String password) {
    return isPlayerUserNameExist(username) & isPasswordExist(ID, password);
  }

  /**
   * Checks if the player user name is registered to database
   *
   * @param playerUserName A variable of type String
   * @return A Boolean data type
   */
  public boolean isPlayerUserNameExist(String playerUserName) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = "SELECT user_name  FROM player_detail WHERE user_name= ?;";
    boolean exist;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, playerUserName);
      resultSet = preparedStatement.executeQuery();
      exist = resultSet.next();
      preparedStatement.close();
      resultSet.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
      exist = false;
    }
    return exist;
  }

  /**
   * Check Id from players_time table
   *
   * @param playerID player Id
   * @return Boolean value
   */
  public boolean isPlayerIDExistFromPlayersTime(int playerID) {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = "SELECT id FROM players_time WHERE id= ?;";
    boolean exist;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, playerID);
      resultSet = preparedStatement.executeQuery();

      exist = resultSet.next();
      preparedStatement.close();
      resultSet.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
      exist = false;
    }
    return exist;
  }

  /**
   * Checks if the player email is registered to database
   *
   * @param playerEmail A variable of type String
   * @return ABoolean data type
   */
  public boolean isPlayerEmailExist(String playerEmail) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = "SELECT email  FROM player_detail WHERE email= ?;";
    boolean exist;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, playerEmail);
      resultSet = preparedStatement.executeQuery();

      exist = resultSet.next();
      preparedStatement.close();
      resultSet.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
      exist = false;
    }
    return exist;
  }


  /**
   * Checks password  if registered in the database
   *
   * @param ID player ID
   */
  public boolean isPasswordExist(int ID, String enteredPassword) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = "SELECT password  FROM player_detail WHERE id= ?;";
    boolean exist = false;
    String reg = null;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, ID);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        reg = resultSet.getString("password");
        exist = HashPassword.validatePassword(enteredPassword, reg);
      }

      preparedStatement.close();
      resultSet.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
      exist = false;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return exist;
  }

  /**
   * Checks id from leader_board table
   *
   * @param playerID player Id
   * @return Boolean value
   */
  public boolean isPlayerIDExistFromLeaderBoard(int playerID) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = "SELECT id FROM leader_board WHERE id= ?;";
    boolean exist;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, playerID);
      resultSet = preparedStatement.executeQuery();

      exist = resultSet.next();
      preparedStatement.close();
      resultSet.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
      exist = false;
    }
    return exist;
  }

  /**
   * Deletes time from Players_time table with the given ID
   *
   * @param ID player ID
   */
  public void deletePlayerTime(int ID) {
    String deleteQuery = "DELETE FROM players_time WHERE id=?;";
    Connection connection;
    PreparedStatement preparedStatement;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(deleteQuery);
      preparedStatement.setInt(1, ID);
      preparedStatement.execute();

      preparedStatement.close();
      connection.close();
    } catch (SQLException ex) {
      printSQLException(ex);
    }

  }

  /**
   * To delete player's detail from player_detail table
   *
   * @param id ID
   */
  public void deletePlayerDetail(int id) {
    String deleteQuery = "DELETE FROM player_detail WHERE id=?;";
    Connection connection;
    PreparedStatement preparedStatement;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(deleteQuery);
      preparedStatement.setInt(1, id);
      preparedStatement.execute();

      preparedStatement.close();
      connection.close();
    } catch (SQLException ex) {
      printSQLException(ex);
    }
  }

  /**
   * To delete leader_board row in a given ID
   *
   * @param ID id of player
   */
  public void deleteLeaderBoardTime(int ID) {
    String deleteQuery = "DELETE FROM leader_board WHERE id=?;";
    Connection connection;
    PreparedStatement preparedStatement;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(deleteQuery);
      preparedStatement.setInt(1, ID);
      preparedStatement.execute();

      preparedStatement.close();
      connection.close();
    } catch (SQLException ex) {
      printSQLException(ex);
    }

  }

  /**
   * Count number of rows for given table
   *
   * @param table Database table name as String
   * @return Number of rows
   */
  public int countRow(String table) throws SQLException {

    Statement stmt = null;
    ResultSet rs = null;
    Connection connection;
    int rowCount;
    try {
      connection = createConnection();
      stmt = connection.createStatement();
      rs = stmt.executeQuery("SELECT COUNT(*) FROM " + table);
      rs.next();
      rowCount = rs.getInt(1);
    } finally {
      assert rs != null;
      rs.close();
      stmt.close();
    }
    return rowCount;
  }

  /**
   * Sets the leader board with the values
   *
   * @param playerID A variable of type Integer
   * @param playerTime A variable of type String
   * @param playerTrackID A variable of type Integer
   * @param playerRank A variable of type Integer
   */
  public void insertTimingToLeaderBoard(int playerID, int playerTime, int playerTrackID,
      int playerRank) {
    Connection connection;
    PreparedStatement preparedStatement;
    String insertQuery = "INSERT INTO leader_board(id,time,track_id, player_rank) VALUES(?,?,?,?);";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setInt(1, playerID);
      preparedStatement.setInt(2, playerTime);
      preparedStatement.setInt(3, playerTrackID);
      preparedStatement.setInt(4, playerRank);

      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  /**
   * Update the leader board
   *
   * @param playerID A variable of type Integer
   * @param time A variable of type String
   * @param playerTrackID A variable of type Integer
   */
  public void updateLeaderBoard(int playerID, int time, int playerTrackID) {
    Connection connection;
    PreparedStatement preparedStatement;
    String updateLeaderBoard = "UPDATE leader_board SET time =?,track_id=? WHERE id= ?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(updateLeaderBoard);
      preparedStatement.setInt(3, playerID);
      preparedStatement.setInt(1, time);
      preparedStatement.setInt(2, playerTrackID);
      preparedStatement.executeUpdate();

      preparedStatement.close();
      connection.close();

    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  /**
   * Retrieves Time to update leader board
   *
   * @param playerTrackID List of tracks  A variable of type ArrayList
   * @return An ArrayList data type
   */
  public ArrayList<Time> getLeaderBoard(ArrayList<Integer> playerTrackID) {
    updatePlayersLevel();
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    ArrayList<Time> list = new ArrayList<>();
    String getBoardQuery =
        "SELECT player_detail.id,player_detail.user_name,leader_board.time,leader_board.player_rank FROM player_detail "
            +
            "INNER JOIN leader_board ON player_detail.id=leader_board.id AND leader_board.track_id=?;";
    for (Integer integer : playerTrackID) {
      try {
        connection = createConnection();
        preparedStatement = connection.prepareStatement(getBoardQuery);
        preparedStatement.setInt(1, integer);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
          int ID = resultSet.getInt("id");
          String playerUserName = resultSet.getString("user_name");
          int time = resultSet.getInt("time");
          int rank = resultSet.getInt("player_rank");

          Time playerTime = new Time(time, playerUserName, ID, rank);
          list.add(playerTime);
        }
        preparedStatement.close();
        resultSet.close();
        connection.close();
      } catch (SQLException e) {
        printSQLException(e);
      }
    }
    return list;

  }

  /**
   * To retrieve all registered Time object in the Players_time table in a given track
   *
   * @param playerTrackID track
   * @return ArrayList of Time
   */
  public ArrayList<Time> getPlayersTime(int playerTrackID) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    ArrayList<Time> list = new ArrayList<>();
    String getBoardQuery =
        "SELECT player_detail.id,player_detail.user_name,players_time.time FROM player_detail " +
            "INNER JOIN players_time ON player_detail.id=players_time.id AND players_time.track_id=?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(getBoardQuery);
      preparedStatement.setInt(1, playerTrackID);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        int ID = resultSet.getInt("id");
        String playerUserName = resultSet.getString("user_name");
        int time = resultSet.getInt("time");

        Time playerTime = new Time(time, playerUserName, ID);
        list.add(playerTime);
      }
      preparedStatement.close();
      resultSet.close();
      connection.close();
    } catch (SQLException e) {
      printSQLException(e);
    }
    return list;

  }

  /**
   * Retrieves time from the leader board for a given ID
   *
   * @param playerID player ID
   * @return time as an Integer
   */

  public int getTimeFromLeaderBoard(int playerID) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String query = "SELECT time from leader_board where id=?;";
    int time = 0;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, playerID);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {

        time = resultSet.getInt("time");

      }

      preparedStatement.close();
      resultSet.close();
      connection.close();
      return time;
    } catch (SQLException ex) {
      printSQLException(ex);
      return 0;
    }
  }

  /**
   * To retrieve a track id from leader_board tableF
   *
   * @param playerID player ID
   * @return track_id
   */
  public int getPlayerTrackIDFromLeaderBoard(int playerID) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    int track = 1;
    String detailQuery = "SELECT leader_board.track_id FROM leader_board where leader_board.id=?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(detailQuery);
      preparedStatement.setInt(1, playerID);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        track = resultSet.getInt("track_id");
      }
      preparedStatement.close();
      resultSet.close();
      connection.close();
      return track;
    } catch (SQLException ex) {
      printSQLException(ex);
      return track;
    }
  }

  /**
   * Retrieves Time to update  players_time table
   *
   * @param playerID player ID
   * @return time as Integer
   */
  public int getTime(int playerID) {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    int time = 0;
    String query = "SELECT time from players_time where id=?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, playerID);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        time = resultSet.getInt("time");

      }

      preparedStatement.close();
      resultSet.close();
      connection.close();
      return time;
    } catch (SQLException ex) {
      printSQLException(ex);
      return 0;
    }
  }

  /**
   * To update players position or level in the given leader board
   */
  public void updatePlayersLevel() {
    Connection connection;
    Statement statement;
    ResultSet resultSets;
    String rankQuery = "SELECT id, RANK() OVER ( PARTITION BY (track_id) ORDER BY (time)) player_rank FROM leader_board;";
    try {
      connection = createConnection();
      statement = connection.createStatement();
      resultSets = statement.executeQuery(rankQuery);
      while (resultSets.next()) {

        int ID = resultSets.getInt("id");

        int my_rank = resultSets.getInt("player_rank");
        setPlayerLevel(ID, my_rank);
      }

      resultSets.close();
      statement.close();
      resultSets.close();
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  /**
   * Best time recorded in a given track
   *
   * @return Time
   */
  public Time bestTime(int trackID) {

    Connection connection;
    Statement statement;
    ResultSet resultSets;
    Time bestTime = null;
    String getBoardQuery =
        "SELECT player_detail.id,player_detail.user_name,players_time.time FROM player_detail " +
            "INNER JOIN players_time ON player_detail.id=players_time.id  where track_id=" + trackID
            + " ORDER BY time limit 1;";
    try {
      connection = createConnection();
      statement = connection.createStatement();
      resultSets = statement.executeQuery(getBoardQuery);
      while (resultSets.next()) {

        int ID = resultSets.getInt("id");
        String playerUserName = resultSets.getString("user_name");
        int time = resultSets.getInt("time");
        bestTime = new Time(time, playerUserName, ID);

      }
      resultSets.close();
      statement.close();
      resultSets.close();
    } catch (SQLException e) {
      printSQLException(e);
    }
    return bestTime;

  }

  /**
   * To insert players time to Players_time table
   *
   * @param playerID ID
   * @param time time
   * @param trackID trackID
   */
  public void insertPlayersTime(int playerID, int time, int trackID) {
    String insertQuery = "INSERT INTO players_time(id,time,track_id) VALUES(?,?,?);";
    Connection connection;
    PreparedStatement preparedStatement;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(insertQuery);
      preparedStatement.setInt(1, playerID);
      preparedStatement.setInt(2, time);
      preparedStatement.setInt(3, trackID);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Update player's time from Players_time table
   *
   * @param playerID ID
   * @param time time
   * @param trackId trackID
   */
  public void updatePlayersTime(int playerID, int time, int trackId) {
    Connection connection;
    PreparedStatement preparedStatement;
    String update = "UPDATE players_time SET time =?,track_id=? WHERE id= ?;";
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(update);
      preparedStatement.setInt(1, time);
      preparedStatement.setInt(2, trackId);
      preparedStatement.setInt(3, playerID);
      preparedStatement.executeUpdate();

      preparedStatement.close();
      connection.close();

    } catch (SQLException e) {
      printSQLException(e);
    }

  }

  /**
   * To Set the position of player in a given ID and rank
   *
   * @param ID ID
   * @param rank position
   */
  public void setPlayerLevel(int ID, int rank) {
    String updateQuery = "UPDATE leader_board SET player_rank =? WHERE id= ?;";
    Connection connection;
    PreparedStatement preparedStatement;
    try {
      connection = createConnection();
      preparedStatement = connection.prepareStatement(updateQuery);
      preparedStatement.setInt(1, rank);
      preparedStatement.setInt(2, ID);
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.close();

    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  /**
   * Prints exception
   *
   * @param e sql exception
   */
  public void printSQLException(SQLException e) {
    e.printStackTrace(System.err);

  }

}
