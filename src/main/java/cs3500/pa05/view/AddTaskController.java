package cs3500.pa05.view;

import cs3500.pa05.model.AddTask;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Controller class for the AddTask view.
 */

public class AddTaskController {
  @FXML
  private TextField txtName;

  @FXML
  private TextField txtDescription;

  @FXML
  private ComboBox<String> cmbDay;

  @FXML
  private CheckBox chkCompleted;

  /**
   * Sets the task model for the view.
   *
   * @param task The AddTask model to bind to the view.
   */

  @FXML
  public void setTask(AddTask task) {
    txtName.textProperty().bindBidirectional(task.name());
    txtDescription.textProperty().bindBidirectional(task.description());
    cmbDay.valueProperty().bindBidirectional(task.day());

    chkCompleted.selectedProperty().bindBidirectional(task.isCompleted());
  }
}
