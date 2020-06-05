package Server.Request;

import Database.DatabaseControl;
import Server.Response.DefaultResponse;
import Server.Response.Response;

public class UpdateLeaderboardRequest implements Request, DatabaseRequest {

  private int playerId;
  private int trackId;
  private int time;
  private DatabaseControl databaseControl;

  public UpdateLeaderboardRequest(int playerId, int trackId, int time) {
    this.playerId = playerId;
    this.trackId = trackId;
    this.time = time;
  }

  @Override
  public Response handleRequest() {
    databaseControl.createConnection();

    databaseControl.updateLeaderBoard(playerId, time, trackId);

    return new DefaultResponse(1);
  }

  @Override
  public void setDatabase(DatabaseControl databaseControl) {
    this.databaseControl = databaseControl;
  }
}
