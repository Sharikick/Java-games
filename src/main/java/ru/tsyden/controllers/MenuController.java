package ru.tsyden.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ru.tsyden.Main;
import ru.tsyden.Util;
import ru.tsyden.constants.Xml;

public class MenuController {
  @FXML private Button snakeRoute;

  @FXML
  private void initialize() {
    this.snakeRoute.setOnAction(event -> this.handleMoveViewSnake(event));
  }

  private void handleMoveViewSnake(ActionEvent event) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(Xml.SNAKE.getPath()));
      Pane root = loader.load();
      SnakeController snakeController = loader.getController();
      Scene scene = new Scene(root);
      scene.setOnKeyPressed(e -> snakeController.keyHandler(e.getCode()));
      Util.createWindow(Xml.SNAKE.getTitle(), Main.stage, scene);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
