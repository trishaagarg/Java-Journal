package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task in the journal.
 *
 * @param id          The ID of the task.
 * @param name        The name of the task.
 * @param description The description of the task.
 * @param day         The day of the task.
 * @param isCompleted Indicates whether the task is completed or not.
 */
public record Task(
    @JsonProperty("id") int id,
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") String day,
    @JsonProperty("isCompleted") boolean isCompleted) {
}
