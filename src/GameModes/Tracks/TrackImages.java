package GameModes.Tracks;

import GameModes.Multiplayer.GameModes.MultiplayerGame;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class TrackImages {

  String imageName;
  Image image;
  ImagePattern topLeft;
  ImagePattern topRight;
  ImagePattern bottomLeft;
  ImagePattern bottomRight;
  ImagePattern horizontal;
  ImagePattern vertical;
  ImagePattern finish;
  ImagePattern oil;
  ImagePattern background;
  ImagePattern star;
  ImagePattern backRoute;
  ImagePattern randomFinish;

  public TrackImages() {
    createImages();
  }

  public ImagePattern getRandomFinish() {
    return randomFinish;
  }

  public ImagePattern getBackRoute() {
    return backRoute;
  }

  public ImagePattern getStar() {
    return star;
  }

  public ImagePattern getTopLeft() {
    return topLeft;
  }

  public ImagePattern getTopRight() {
    return topRight;
  }

  public ImagePattern getBottomLeft() {
    return bottomLeft;
  }

  public ImagePattern getBottomRight() {
    return bottomRight;
  }

  public ImagePattern getHorizontal() {
    return horizontal;
  }

  public ImagePattern getVertical() {
    return vertical;
  }

  public ImagePattern getFinish() {
    return finish;
  }

  public ImagePattern getOil() {
    return oil;
  }

  public ImagePattern getBackground() {
    return background;
  }

  public void createImages() {
    imageName = MultiplayerGame.class.getResource("/Resources/topLeft.png").toExternalForm();
    image = new Image(imageName);
    topLeft = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/topRight.png").toExternalForm();
    image = new Image(imageName);
    topRight = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/bottomLeft.png").toExternalForm();
    image = new Image(imageName);
    bottomLeft = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/bottomRight.png").toExternalForm();
    image = new Image(imageName);
    bottomRight = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/horizontal.png").toExternalForm();
    image = new Image(imageName);
    horizontal = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/vertical.png").toExternalForm();
    image = new Image(imageName);
    vertical = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/oil.png").toExternalForm();
    image = new Image(imageName);
    oil = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/background.png").toExternalForm();
    image = new Image(imageName);
    background = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/routeFinishLine.png")
        .toExternalForm();
    image = new Image(imageName);
    finish = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/star.png").toExternalForm();
    image = new Image(imageName);
    star = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/route.png").toExternalForm();
    image = new Image(imageName);
    backRoute = new ImagePattern(image);
    imageName = MultiplayerGame.class.getResource("/Resources/routeFinishLineRandom.png")
        .toExternalForm();
    image = new Image(imageName);
    randomFinish = new ImagePattern(image);

  }
}
