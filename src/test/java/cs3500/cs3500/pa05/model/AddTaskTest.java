package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.AddTask;
import cs3500.pa05.model.Task;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

/**
 * Test class for the AddTask class.
 */
class AddTaskTest {
  /**
   * Test case for converting an AddTask object to a Task object.
   */
  @Test
  void toTask() {

    StringProperty nameProperty = new SimpleStringProperty("Task 1");
    StringProperty descriptionProperty = new SimpleStringProperty("Task description");
    StringProperty dayProperty = new SimpleStringProperty("Monday");
    BooleanProperty isCompletedProperty = new SimpleBooleanProperty(true);

    AddTask addTask = new AddTask(nameProperty, descriptionProperty, dayProperty,
        isCompletedProperty);

    final Task task = addTask.toTask();
    addTask.name();
    addTask.isCompleted();
    addTask.day();
    addTask.description();

    assertEquals("Task 1", task.name());
    assertEquals("Task description", task.description());
    assertEquals("Monday", task.day());
    assertEquals(true, task.isCompleted());
  }
}
