package cs3500.pa05.view;

import cs3500.pa05.model.AddConstraint;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for the AddConstraint view.
 *
 */

public class AddConstraintController {
  @FXML
  private TextField txtMaxTasksPerDay;

  @FXML
  private TextField txtMaxEventsPerDay;

  /**
   * Sets the constraint model for the view.
   *
   * @param constraint The AddConstraint model to bind to the view.
   */
  @FXML
  public void setConstraint(AddConstraint constraint) {
    txtMaxTasksPerDay.textProperty().bindBidirectional(constraint.maxTasksPerDay());
    txtMaxEventsPerDay.textProperty().bindBidirectional(constraint.maxEventsPerDay());
  }
}