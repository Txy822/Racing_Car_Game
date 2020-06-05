package Server.Request;

import Database.DatabaseControl;
import Server.Response.GetLeaderboardResponse;
import java.util.ArrayList;

public class GetLeaderboardRequest implements Request, DatabaseRequest {

  private int trackId;
  private DatabaseControl databaseControl;

  public GetLeaderboardRequest(int trackId) {
    this.trackId = trackId;
  }

  @Override
  public GetLeaderboardResponse handleRequest() {

    databaseControl.createConnection();
    ArrayList<Integer> tracks = new ArrayList<>(); //To be finished
    tracks.add(1);//Consider for two tracks
    tracks.add(2);
    databaseControl.getLeaderBoard(tracks);

    return new GetLeaderboardResponse(1, databaseControl.getLeaderBoard(tracks));
  }

  @Override
  public void setDatabase(DatabaseControl databaseControl) {
    this.databaseControl = databaseControl;
  }

  public DatabaseControl getDatabaseControl() {
    return databaseControl;
  }
}
