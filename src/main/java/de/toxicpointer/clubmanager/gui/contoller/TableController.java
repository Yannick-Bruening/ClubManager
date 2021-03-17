package de.toxicpointer.clubmanager.gui.contoller;

import de.toxicpointer.clubmanager.ClubManager;
import de.toxicpointer.clubmanager.club.Club;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class TableController {

  @FXML
  BorderPane contentPane;
  @FXML
  TableView<Club> table;
  @FXML
  TableColumn<Club, String> placeColumn;
  @FXML
  TableColumn<Club, String> clubColumn;
  @FXML
  TableColumn<Club, String> goalColumn;
  @FXML
  TableColumn<Club, String> concededColumn;
  @FXML
  TableColumn<Club, String> pointsColumn;

  @FXML
  public void initialize() {

    placeColumn.setReorderable(false);
    placeColumn.setSortable(false);
    clubColumn.setReorderable(false);
    clubColumn.setSortable(false);
    goalColumn.setReorderable(false);
    goalColumn.setSortable(false);
    concededColumn.setReorderable(false);
    concededColumn.setSortable(false);
    pointsColumn.setReorderable(false);
    pointsColumn.setSortable(false);

    placeColumn.prefWidthProperty().bind(table.widthProperty().multiply(.1));
    clubColumn.prefWidthProperty().bind(table.widthProperty().multiply(.6));
    goalColumn.prefWidthProperty().bind(table.widthProperty().multiply(.1));
    concededColumn.prefWidthProperty().bind(table.widthProperty().multiply(.1));
    pointsColumn.prefWidthProperty().bind(table.widthProperty().multiply(.09));

    table.setPlaceholder(new Label("Keine Vereine Vorhanden"));

    final ObservableList<Club> data = FXCollections.observableArrayList(ClubManager.clubDataManager.get().getSortedClubs());
    table.setItems(data);
    placeColumn.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(p.getValue()) + 1 + ""));
    clubColumn.setCellValueFactory(new PropertyValueFactory<>("clubName"));
    goalColumn.setCellValueFactory(new PropertyValueFactory<>("goals"));
    concededColumn.setCellValueFactory(new PropertyValueFactory<>("conceded"));
    pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
  }
}
