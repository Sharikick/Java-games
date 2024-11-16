package ru.tsyden.entities;

import java.util.Random;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import ru.tsyden.Point;
import ru.tsyden.Util;
import ru.tsyden.constants.SnakeVars;

public class Food {
  private Random random;
  private Rectangle food;
  private Point position;

  public Food() {
    this.random = new Random();
    this.position = new Point();
  }

  public Point getPosition() {
    return this.position;
  }

  public void generateFood(Group gameMap) {
    this.position.setX(this.random.nextInt(SnakeVars.COUNT_BLOCK));
    this.position.setY(this.random.nextInt(SnakeVars.COUNT_BLOCK));

    this.food = Util.createFoodBlock(this.position.getX(), this.position.getY());

    gameMap.getChildren().add(this.food);
  }

  public void render(Group gameMap) {
    gameMap.getChildren().remove(this.food);
    this.generateFood(gameMap);
  }
}
