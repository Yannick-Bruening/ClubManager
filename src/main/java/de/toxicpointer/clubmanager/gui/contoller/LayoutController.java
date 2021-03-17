package de.toxicpointer.clubmanager.gui.contoller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class LayoutController {
  private double offsetX;
  private double offsetY;
  private static final AtomicReference<LayoutController> instance = new AtomicReference<>();
  private String loadedPath = "";

  @FXML
  HBox menuBar;
  @FXML
  VBox lyNavbar;
  @FXML
  Label menuClose;
  @FXML
  Label menuMinimize;
  @FXML
  AnchorPane contentPane;

  @FXML
  public void initialize() {
    instance.set(this);

    final Font solid = Font.loadFont(getClass().getResourceAsStream("/fonts/faFreeSolid.otf"), 20);
    final Font regular = Font.loadFont(getClass().getResourceAsStream("/fonts/faFreeRegular.otf"), 20);

    menuClose.setFont(solid);
    menuMinimize.setFont(regular);

    menuClose.setText("\uf00d");
    menuMinimize.setText("\uf2d1");

    loadFXMLIntoContentPane("/Table.fxml");
  }

  @FXML
  public void onMenuMousePress(final MouseEvent event) {
    offsetX = event.getSceneX();
    offsetY = event.getSceneY();
  }

  @FXML
  public void onMenuMouseDrag(final MouseEvent event) {
    final Node source = (Node) event.getSource();
    final Window window = source.getScene().getWindow();

    window.setX(event.getScreenX() - offsetX);
    window.setY(event.getScreenY() - offsetY);
    window.setOpacity(0.8f);
  }

  @FXML
  public void onMenuMouseRelease(final MouseEvent event) {
    final Node source = (Node) event.getSource();
    final Window window = source.getScene().getWindow();

    window.setOpacity(1f);
  }

  @FXML
  public void onMinimizeClicked(final MouseEvent event) {
    final Node source = (Node) event.getSource();
    final Stage window = (Stage) source.getScene().getWindow();
    window.setIconified(true);
  }

  @FXML
  public void onCloseClicked(final MouseEvent event) {
    final Node source = (Node) event.getSource();
    final Stage window = (Stage) source.getScene().getWindow();
    window.close();
  }

  @FXML
  public void onClickTable(final ActionEvent event) {
    loadFXMLIntoContentPane("/Table.fxml");
  }

  @FXML
  public void onClickClubs(final ActionEvent event) {
    loadFXMLIntoContentPane("/Clubs.fxml");
  }

  @FXML
  public void onClickGames(final ActionEvent event) {
    loadFXMLIntoContentPane("/Games.fxml");
  }


  public void loadFXMLIntoContentPane(final String path) {
    loadFXMLIntoContentPane(path, false);
  }

  public void loadFXMLIntoContentPane(final String path, final boolean force) {
    try {
      if (loadedPath.equals(path) && !force) {
        return;
      }
      loadedPath = path;
      final Pane loaded = FXMLLoader.load(getClass().getResource(path));

      AnchorPane.setTopAnchor(loaded, 0.0);
      AnchorPane.setBottomAnchor(loaded, 0.0);
      AnchorPane.setLeftAnchor(loaded, 0.0);
      AnchorPane.setRightAnchor(loaded, 0.0);
      contentPane.getChildren().setAll(loaded);
    } catch (final IOException exception) {
      exception.printStackTrace();
    }
  }

  public static LayoutController getInstance() {
    return instance.get();
  }
}
