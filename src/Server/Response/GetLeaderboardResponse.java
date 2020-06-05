package Server.Response;

import Database.Time;
import java.util.ArrayList;

public class GetLeaderboardResponse implements Response {

  private int code;
  private ArrayList<Time> leaderboard;

  public GetLeaderboardResponse(int code, ArrayList<Time> leaderboard) {
    this.code = code;
    this.leaderboard = leaderboard;
  }

  @Override
  public int getCode() {
    return 0;
  }

  public ArrayList<Time> getLeaderboard() {
    return leaderboard;
  }
}
