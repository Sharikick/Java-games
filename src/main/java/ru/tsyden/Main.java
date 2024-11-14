package ru.tsyden;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.tsyden.constants.Xml;

public class Main extends Application {
  private static final int WINDOW_HEIGHT = 800;
  private static final int WINDOW_WIDTH = 1200;

  public static Stage stage;

  private void setStage(Stage stage) {
    stage.setHeight(WINDOW_HEIGHT);
    stage.setWidth(WINDOW_WIDTH);
    stage.setResizable(false);
    stage.centerOnScreen();

    Main.stage = stage;
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    this.setStage(stage);
    FXMLLoader loader = new FXMLLoader(getClass().getResource(Xml.MENU.getPath()));
    Pane root = loader.load();
    Util.createWindow(Xml.MENU.getTitle(), Main.stage, new Scene(root));
  }
}
