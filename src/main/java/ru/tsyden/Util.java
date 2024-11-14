package ru.tsyden;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ru.tsyden.constants.SnakeVars;

public class Util {
  public static void createWindow(String title, Stage stage, Scene scene) {
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
  }

  public static Rectangle createSegment(int x, int y, Color color) {
    Rectangle segment = new Rectangle(SnakeVars.SEGMENT_SIZE, SnakeVars.SEGMENT_SIZE);
    segment.setFill(color);
    segment.setX((SnakeVars.BLOCK_SIZE - SnakeVars.SEGMENT_SIZE) / 2 + x * SnakeVars.BLOCK_SIZE);
    segment.setY((SnakeVars.BLOCK_SIZE - SnakeVars.SEGMENT_SIZE) / 2 + y * SnakeVars.BLOCK_SIZE);
    return segment;
  }
}
