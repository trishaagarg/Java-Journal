package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Constraint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test class for the Constraint class.
 */
class ConstraintTest {
  private Constraint constraint;

  /**
   * Sets up the test fixture before each test case.
   */
  @BeforeEach
  void setUp() {
    constraint = new Constraint();
  }

  /**
   * Test case for getting the maximum tasks per day.
   */
  @Test
  void getMaxTasksPerDay() {
    constraint.setMaxTasksPerDay("2");
    assertEquals("2", constraint.getMaxTasksPerDay());
  }

  /**
   * Test case for setting the maximum tasks per day.
   */
  @Test
  void setMaxTasksPerDay() {
    constraint.setMaxTasksPerDay("3");
    assertEquals("3", constraint.getMaxTasksPerDay());
  }

  /**
   * Test case for getting the maximum events per day.
   */
  @Test
  void getMaxEventsPerDay() {
    constraint.setMaxEventsPerDay("1");
    assertEquals("1", constraint.getMaxEventsPerDay());
  }

  /**
   * Test case for setting the maximum events per day.
   */
  @Test
  void setMaxEventsPerDay() {
    constraint.setMaxEventsPerDay("4");
    assertEquals("4", constraint.getMaxEventsPerDay());
  }
}