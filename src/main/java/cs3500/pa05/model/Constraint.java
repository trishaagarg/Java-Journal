package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the constraints for the journal.
 */

public class Constraint {
  @JsonProperty("maxTasksPerDay") String maxTasksPerDay;
  @JsonProperty("maxEventsPerDay") String maxEventsPerDay;

  /**
   * Gets the maximum number of tasks allowed per day.
   *
   * @return The maximum tasks per day.
   */
  public String getMaxTasksPerDay() {
    return maxTasksPerDay;
  }

  /**
   * Sets the maximum number of tasks allowed per day.
   *
   * @param maxTasksPerDay The maximum tasks per day.
   */
  public void setMaxTasksPerDay(String maxTasksPerDay) {
    this.maxTasksPerDay = maxTasksPerDay;
  }

  /**
   * Gets the maximum number of events allowed per day.
   *
   * @return The maximum events per day.
   */
  public String getMaxEventsPerDay() {
    return maxEventsPerDay;
  }


  /**
   * Sets the maximum number of events allowed per day.
   *
   * @param maxEventsPerDay The maximum events per day.
   */
  public void setMaxEventsPerDay(String maxEventsPerDay) {
    this.maxEventsPerDay = maxEventsPerDay;
  }
}