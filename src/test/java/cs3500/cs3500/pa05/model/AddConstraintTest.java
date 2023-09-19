package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.AddConstraint;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

/**
 * Test class for the AddConstraint class.
 */
class AddConstraintTest {
  /**
   * Test case for creating an AddConstraint object and accessing its properties.
   */
  @Test
  void testAddConstraint() {
    StringProperty maxTasksPerDay = new SimpleStringProperty("2");
    StringProperty maxEventsPerDay = new SimpleStringProperty("3");

    AddConstraint addConstraint = new AddConstraint(maxTasksPerDay, maxEventsPerDay);

    assertEquals("2", addConstraint.maxTasksPerDay().get());
    assertEquals("3", addConstraint.maxEventsPerDay().get());
  }
}
