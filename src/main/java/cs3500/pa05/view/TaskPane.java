package cs3500.pa05.view;

import cs3500.pa05.model.Task;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

/**
 * Represents a pane that displays tasks.
 */
public class TaskPane extends VBox {
  private final List<Task> tasks;
  /**
   * Constructs a TaskPane with the specified list of tasks.
   *
   * @param tasks The list of tasks to display.
   */

  public TaskPane(List<Task> tasks) {
    this.tasks = tasks;
    load();
  }

  private Task task;

  /**
   * Constructs a TaskPane with the specified list of tasks and a specific task.
   *
   * @param tasks The list of tasks to display.
   * @param task  The specific task.
   */

  public TaskPane(List<Task> tasks, Task task) {
    this.tasks = tasks;
    this.task = task;
  }
  /**
   * Loads the tasks into the TaskPane.
   */

  private void load() {
    for (Task task : tasks) {
      addTask(task);
    }
  }

  /**
   * Adds a task to the TaskPane.
   *
   * @param task The task to add.
   */
  public void addTask(Task task) {
    VBox vBox = new VBox();
    addLabel(vBox, task.name());
    addLabel(vBox, task.description());
    addLabel(vBox, "Done?: " + task.isCompleted());
    vBox.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: lightgreen;");
    vBox.setMaxWidth(170);
    vBox.setMaxHeight(160);
    this.getChildren().add(vBox);
  }

  /**
   * Adds a label to the specified VBox with the given value.
   *
   * @param vBox  The VBox to add the label to.
   * @param value The value of the label.
   */
  private void addLabel(VBox vBox, String value) {
    Label label = new Label();
    label.setStyle("-fx-font-size:14;");
    label.setText(value);
    label.setTooltip(new Tooltip(value));
    vBox.getChildren().add(label);
  }

  /**
   * Returns the specific task associated with the TaskPane.
   *
   * @return The specific task.
   */

  public Task getTask() {
    return task;
  }
}

