package de.toxicpointer.clubmanager.gui.contoller;

import de.toxicpointer.clubmanager.ClubManager;
import de.toxicpointer.clubmanager.club.Club;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ClubsController {
  private Club newClub = new Club();

  @FXML
  Button saveButton;
  @FXML
  TextField uuidField;
  @FXML
  TextField nameField;
  @FXML
  TextField goalField;
  @FXML
  TextField concededField;
  @FXML
  TextField pointsField;

  @FXML
  public void initialize() {
    uuidField.textProperty().setValue(newClub.getClubUuid().toString());

    addNumberListener(goalField);
    addNumberListener(concededField);
    addNumberListener(pointsField);
  }

  @FXML
  public void onClickSave(final ActionEvent event) {
    if (goalField.lengthProperty().get() == 0) {
      goalField.setText("0");
    }
    if (concededField.lengthProperty().get() == 0) {
      concededField.setText("0");
    }
    if (pointsField.lengthProperty().get() == 0) {
      pointsField.setText("0");
    }

    final boolean savable = nameField.lengthProperty().get() > 0;

    if (savable) {
      newClub.setClubName(nameField.getText());
      newClub.setGoals(Integer.parseInt(goalField.getText()));
      newClub.setConceded(Integer.parseInt(concededField.getText()));
      newClub.setPoints(Integer.parseInt(concededField.getText()));
      ClubManager.clubDataManager.get().addClub(newClub);

      LayoutController.getInstance().loadFXMLIntoContentPane("/Table.fxml");
    }
  }

  private void addNumberListener(final TextField field) {
    field.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d{0,7}")) {
        field.setText(oldValue);
      }
    });
  }
}
