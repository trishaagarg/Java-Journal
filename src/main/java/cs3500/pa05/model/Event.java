package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event in the journal.
 *
 * @param id          The ID of the event.
 * @param name        The name of the event.
 * @param description The description of the event.
 * @param day         The day of the event.
 * @param startTime   The start time of the event.
 * @param duration    The duration of the event.
 */
public record Event(
    @JsonProperty("id") int id,
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("day") String day,
    @JsonProperty("startTime") String startTime,
    @JsonProperty("duration") int duration) {
}
