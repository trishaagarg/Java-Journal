package cs3500.pa05.model;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

/**
 *Represents an additional task to be added to the journal.
 *
 * @param name The name of the task.
 * @param description  The description of the task.
 * @param day The day of the task.
 * @param isCompleted The completion status of the task.
 */
public record AddTask(
    StringProperty name,
    StringProperty description,
    StringProperty day,
    BooleanProperty isCompleted
) {

  /**
   * Converts the AddTask instance to a Task instance.
   *
   * @return The Task instance representing the AddTask.
   */
  public Task toTask() {
    Task task = new Task(1, name.getValue(), description.getValue(), day.getValue(),
        isCompleted.get());
    return task;
  }
}
