package ru.tsyden.entities;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.tsyden.EndGame;
import ru.tsyden.Position;
import ru.tsyden.Util;
import ru.tsyden.constants.Direction;

public class Snake {
  private Direction direction;
  private List<Rectangle> positions;
  private List<Position> segments;

  public Snake() {
    this.positions = new ArrayList<Rectangle>();
    this.direction = Direction.DOWN;
    this.segments = new ArrayList<Position>();
    this.segments.add(new Position(0, 1));
    this.segments.add(new Position(0, 2));
  }

  public void initializationSnake(Group gameMap) {
    for (Position pos : this.segments) {
      Rectangle segment = Util.createSegment(pos.getX(), pos.getY(), Color.GREEN);
      this.positions.add(segment);
      gameMap.getChildren().add(segment);
    }
  }

  public boolean move(Food food, EndGame end) {
    boolean eat = true;
    Position head = this.segments.get(this.segments.size() - 1);
    Position next =
        switch (this.direction) {
          case Direction.UP -> new Position(head.getX(), head.getY() - 1);
          case Direction.LEFT -> new Position(head.getX() - 1, head.getY());
          case Direction.DOWN -> new Position(head.getX(), head.getY() + 1);
          case Direction.RIGHT -> new Position(head.getX() + 1, head.getY());
        };
    if (next.getX() < 0 || next.getX() > 9 || next.getY() < 0 || next.getY() > 9) {
      end.endGame();
      return false;
    }

    for (Position pos : this.segments) {
      if (pos.getX() == head.getX() && pos.getY() == head.getY()) {
        System.out.println(pos.getX());
        System.out.println(pos.getY());
        System.out.println(head.getX());
        System.out.println(head.getY());
        end.endGame();
        return false;
      }
    }

    if (food.getX() != next.getX() || food.getY() != next.getY()) {
      this.segments.remove(0);
      eat = false;
    }

    this.segments.add(next);
    return eat;
  }

  public void render(Group gameMap) {
    gameMap.getChildren().removeAll(this.positions);
    this.positions.clear();
    this.initializationSnake(gameMap);
  }

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
}
