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

  public static Rectangle createFoodBlock(int x, int y) {
    Rectangle block = new Rectangle(SnakeVars.SEGMENT_SIZE, SnakeVars.SEGMENT_SIZE);
    block.setFill(Color.RED);
    block.setX(calculatePosOfSegment(x));
    block.setY(calculatePosOfSegment(y));
    return block;
  }

  public static Rectangle createSnakeBlock(int x, int y) {
    Rectangle block = new Rectangle(SnakeVars.SEGMENT_SIZE, SnakeVars.SEGMENT_SIZE);
    block.setFill(Color.GREEN);
    block.setX(calculatePosOfSegment(x));
    block.setY(calculatePosOfSegment(y));
    return block;
  }

  public static Rectangle createFieldBlock(int x, int y) {
    Rectangle block = new Rectangle(SnakeVars.BLOCK_SIZE, SnakeVars.BLOCK_SIZE);
    block.setFill(Color.WHITE);
    block.setStroke(Color.web("#333"));
    block.setX(calculatePosOfSegment(x));
    block.setY(calculatePosOfSegment(y));
    return block;
  }

  public static int calculatePosOfSegment(int n) {
    return (SnakeVars.BLOCK_SIZE - SnakeVars.SEGMENT_SIZE) / 2 + n * SnakeVars.BLOCK_SIZE;
  }
}
