package cs3500.pa05.model;

import javafx.beans.property.StringProperty;

/**
 * Represents an additional event to be added to the journal.
 *
 * @param name the name of event
 * @param description The description of the event.
 * @param day  The day of the event.
 * @param startTime the start time of the event.
 * @param duration The duration of the event.
 */
public record AddEvent(
    StringProperty name,
    StringProperty description,
    StringProperty day,
    StringProperty startTime,
    StringProperty duration) {

  /**
   * Converts the AddEvent instance to an Event instance.
   *
   * @return The Event instance representing the AddEvent.
   */
  public Event toEvent() {
    Event event = new Event(1, name.getValue(), description.getValue(),
        day.getValue(), startTime.getValue(), Integer.parseInt(duration.getValue()));
    return event;
  }
}
