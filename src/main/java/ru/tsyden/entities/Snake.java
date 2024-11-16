package ru.tsyden.entities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import ru.tsyden.EndGame;
import ru.tsyden.Point;
import ru.tsyden.Util;
import ru.tsyden.constants.Direction;
import ru.tsyden.constants.SnakeVars;

public class Snake {
  private Direction direction;
  private List<Point> posSegments;
  private List<Rectangle> segments;
  private Food food;
  private Point head;

  public Snake() {
    this.posSegments = new ArrayList<Point>();
    this.segments = new ArrayList<Rectangle>();
    this.direction = Direction.DOWN;
    Point segment = new Point(1, 1);
    this.posSegments.add(segment);
    this.head = segment;
  }

  // ---------- Get and Set --------------------------

  public Direction getDirection() {
    return this.direction;
  }

  public void setDirection(Direction direction) {
    if (direction == Direction.UP && this.direction == Direction.DOWN
        || direction == Direction.DOWN && this.direction == Direction.UP
        || direction == Direction.LEFT && this.direction == Direction.RIGHT
        || direction == Direction.RIGHT && this.direction == Direction.LEFT) {
      return;
    }

    this.direction = direction;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  // -------------------------------------------------

  public void initializationSnake(Group gameMap) {
    for (Point pos : this.posSegments) {
      Rectangle segment = Util.createSnakeBlock(pos.getX(), pos.getY());
      this.segments.add(segment);
      gameMap.getChildren().add(segment);
    }
  }

  public boolean move(Group gameMap, EndGame callback) {
    boolean eat = true;
    this.head =
        switch (this.direction) {
          case Direction.UP -> new Point(this.head.getX(), this.head.getY() - 1);
          case Direction.LEFT -> new Point(this.head.getX() - 1, this.head.getY());
          case Direction.DOWN -> new Point(this.head.getX(), this.head.getY() + 1);
          case Direction.RIGHT -> new Point(this.head.getX() + 1, this.head.getY());
        };

    this.checkCollisionWithSnake(callback);

    if (this.food.getPosition().getX() != this.head.getX() || this.food.getPosition().getY() != this.head.getY()) {
      this.posSegments.remove(0);
      eat = false;
    }

    this.checkCollisionWithBorder();

    this.posSegments.add(this.head);
    return eat;
  }

  public void render(Group gameMap) {
    gameMap.getChildren().removeAll(this.segments);
    this.segments.clear();
    this.initializationSnake(gameMap);
  }

  private void checkCollisionWithBorder() {
    final int start = 0;
    final int end = SnakeVars.COUNT_BLOCK - 1;
    if (this.head.getX() < start) {
      this.head.setX(end);
    }
    if (this.head.getX() > end) {
      this.head.setX(start);
    }
    if (this.head.getY() < start) {
      this.head.setY(end);
    }
    if (this.head.getY() > end) {
      this.head.setY(start);
    }
  }

  private void checkCollisionWithSnake(EndGame callback) {
    for (Point p : this.posSegments) {
      if (p.getX() == this.head.getX() && p.getY() == this.head.getY()) {
        callback.endGame();
      }
    }
  }
}
