package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.AddEvent;
import cs3500.pa05.model.Event;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.Test;

/**
 * Test class for the AddEvent class.
 */
public class AddEventTest {

  /**
   * Test case for converting an AddEvent object to an Event object.
   */

  @Test
  void toEvent() {
    AddEvent addEvent = new AddEvent(
        new SimpleStringProperty("school"),
        new SimpleStringProperty("do homework"),
        new SimpleStringProperty("Sunday"),
        new SimpleStringProperty("09:00"),
        new SimpleStringProperty("1"));

    Event event = addEvent.toEvent();
    assertEquals(1, event.id());
    assertEquals("school", event.name());
    assertEquals("do homework", event.description());
    assertEquals("Sunday", event.day());
    assertEquals("09:00", event.startTime());
    assertEquals(1, event.duration());
  }

  /**
   * Test case for converting an AddEvent object to an Event object with no description.
   */

  @Test
  void toEventNoDescription() {

    AddEvent addEvent = new AddEvent(
        new SimpleStringProperty("school"),
        new SimpleStringProperty(null),
        new SimpleStringProperty("Sunday"),
        new SimpleStringProperty("09:00"),
        new SimpleStringProperty("1")
    );

    addEvent.day();
    addEvent.name();
    addEvent.description();
    addEvent.duration();
    addEvent.startTime();
    Event event = addEvent.toEvent();

    assertEquals(1, event.id());
    assertEquals("school", event.name());
    assertEquals("Sunday", event.day());
    assertEquals("09:00", event.startTime());
    assertEquals(null, event.description());
    assertEquals(1, event.duration());
  }
}


