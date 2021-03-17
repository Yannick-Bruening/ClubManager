package de.toxicpointer.clubmanager.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClubUI extends Application {
  
  @Override
  public void start(final Stage primaryStage) throws Exception {
    final Parent root = FXMLLoader.load(getClass().getResource("/Layout.fxml"));

    final Scene scene = new Scene(root);

    root.getStyleClass().add("rootpane");
    scene.setFill(Color.TRANSPARENT);

    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo.png")));

    primaryStage.initStyle(StageStyle.TRANSPARENT);
    primaryStage.setTitle("ClubManager 2k21");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
