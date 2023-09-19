
package cs3500.pa05.view;

import cs3500.pa05.model.AddEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller class for the AddEvent view.
 *
 */
public class AddEventController {
  @FXML
  private TextField txtName;

  @FXML
  private TextField txtDescription;

  @FXML
  private ComboBox<String> cmbDay;

  @FXML
  private TextField txtStartTime;

  @FXML
  private TextField txtDuration;

  /**
   * Sets the event model for the view.
   *
   * @param event The AddEvent model to bind to the view.
   */

  @FXML
  public void setEvent(AddEvent event) {
    txtName.textProperty().bindBidirectional(event.name());
    txtDescription.textProperty().bindBidirectional(event.description());
    cmbDay.valueProperty().bindBidirectional(event.day());
    txtStartTime.textProperty().bindBidirectional(event.startTime());
    txtDuration.textProperty().bindBidirectional(event.duration());
  }
}