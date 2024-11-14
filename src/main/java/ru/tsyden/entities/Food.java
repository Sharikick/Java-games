package ru.tsyden.entities;

import java.util.Random;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.tsyden.Util;
import ru.tsyden.constants.SnakeVars;

public class Food {
  private Random random;
  private Rectangle position;
  private int x;
  private int y;

  public Food() {
    this.random = new Random();
  }

  public Rectangle getPosition() {
    return this.position;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public void generateFood(Group gameMap) {
    this.x = this.random.nextInt(SnakeVars.COUNT_BLOCK);
    this.y = this.random.nextInt(SnakeVars.COUNT_BLOCK);
    this.position = Util.createSegment(this.x, this.y, Color.RED);

    gameMap.getChildren().add(this.position);
  }

  public void render(Group gameMap) {
    gameMap
        .getChildren()
        .remove(this.position);
    this.generateFood(gameMap);
  }
}
