package ru.tsyden.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ru.tsyden.Main;
import ru.tsyden.Util;
import ru.tsyden.constants.Direction;
import ru.tsyden.constants.SnakeVars;
import ru.tsyden.constants.Xml;
import ru.tsyden.entities.Food;
import ru.tsyden.entities.Snake;

public class SnakeController {
  private Snake snake;
  private Timeline timeline;
  private Food food;
  private int score;

  @FXML private Label scoreText;

  @FXML private Button menuRoute;

  @FXML private Group gameMap;

  @FXML
  private void initialize() {
    this.menuRoute.setOnAction(event -> this.handleModeViewMenu(event));
    this.initializationGame();
  }

  private void initializationGame() {
    this.score = 0;
    this.snake = new Snake();
    this.food = new Food();

    this.scoreText.setText(Integer.toString(score));
    this.buildGameMap();
    this.snake.initializationSnake(this.gameMap);
    this.food.generateFood(this.gameMap);
    this.snake.setFood(this.food);
    this.timeline =
        new Timeline(
            new KeyFrame(
                Duration.millis(1000 / 18),
                e -> {
                  boolean eat = this.snake.move(
                      this.gameMap,
                      () -> {
                        this.timeline.stop();
                      });
                  if (eat) {
                    this.food.render(this.gameMap);
                    this.scoreText.setText(Integer.toString(++score));
                  }

                  this.snake.render(this.gameMap);
                  System.out.println("PING");
                }));
    this.timeline.setCycleCount(Timeline.INDEFINITE);
    this.timeline.play();
  }

  private void buildGameMap() {
    for (int x = 0; x < SnakeVars.COUNT_BLOCK; x++) {
      for (int y = 0; y < SnakeVars.COUNT_BLOCK; y++) {
        Rectangle block = new Rectangle(SnakeVars.BLOCK_SIZE, SnakeVars.BLOCK_SIZE);
        block.setFill(Color.WHITE);
        block.setStroke(Color.web("#333"));
        block.setX(x * SnakeVars.BLOCK_SIZE);
        block.setY(y * SnakeVars.BLOCK_SIZE);
        this.gameMap.getChildren().add(block);
      }
    }
  }

  public void keyHandler(KeyCode code) {
    switch (code.getName()) {
      case "W":
        this.snake.setDirection(Direction.UP);
        break;
      case "D":
        this.snake.setDirection(Direction.RIGHT);
        break;
      case "A":
        this.snake.setDirection(Direction.LEFT);
        break;
      case "S":
        this.snake.setDirection(Direction.DOWN);
        break;
    }
  }

  private void handleModeViewMenu(ActionEvent event) {
    this.timeline.stop();
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Xml.MENU.getPath()));
      Pane root = loader.load();
      Util.createWindow(Xml.MENU.getTitle(), Main.stage, new Scene(root));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
