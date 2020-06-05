package GameModes.Tracks;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class TrackTest {

  private Track track;
  private TrackManager trackManager;


  @Before
  public void setUp() throws Exception {
    trackManager = new TrackManager();
    track = trackManager.getTrackOne();
  }

  @Test
  public void getLayout() {
    assertNotNull(track.getLayout());
  }

  @Test
  public void setLayout() {
    track.setLayout(trackManager.getLargeTrack().getLayout());
  }

  @Test
  public void getWidth() {
    track.getWidth();
  }

  @Test
  public void setWidth() {
    track.setWidth(trackManager.getLargeTrack().getWidth());
  }

  @Test
  public void getHeight() {
    assertNotNull(track.getHeight());
  }

  @Test
  public void setHeight() {
    track.setHeight(trackManager.getLargeTrack().getHeight());
  }

  @Test
  public void getRectangles() {
    assertNotNull(track.getRectangles());
  }

  @Test
  public void setRectangles() {
    track.setRectangles(trackManager.getLargeTrack().getRectangles());
  }

  @Test
  public void toStringTest() {
    track.toString();
  }

}