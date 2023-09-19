package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Task;
import org.junit.jupiter.api.Test;

/**
 * Test the Task record
 */
class TaskTest {
  /**
   * tests the task record dunctionalirt
   */
  @Test
  void taskRecord() {
    Task task = new Task(1, "homework", "do math homework", "Monday", true);

    // Use assert statements to verify the values of the record fields
    assertEquals(1, task.id());
    assertEquals("homework", task.name());
    assertEquals("do math homework", task.description());
    assertEquals("Monday", task.day());
    assertEquals(true, task.isCompleted());
  }
}
