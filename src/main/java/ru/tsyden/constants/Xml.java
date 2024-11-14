package ru.tsyden.constants;

public enum Xml {
  MENU("/fxml/Menu.fxml", "Меню"),
  SNAKE("/fxml/Snake.fxml", "Змейка");

  private String path;
  private String title;

  private Xml(String path, String title) {
    this.path = path;
    this.title = title;
  }

  public String getPath() {
    return this.path;
  }

  public String getTitle() {
    return this.title;
  }
}
