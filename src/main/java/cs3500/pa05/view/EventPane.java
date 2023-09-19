package cs3500.pa05.view;

import cs3500.pa05.model.Event;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

/**
 * Represents a pane that displays a list of events.
 */
public class EventPane extends VBox {
  private final List<Event> events;
  private Event event;

  /**
   * Constructs an EventPane with the specified list of events.
   *
   * @param events The list of events to display.
   */
  public EventPane(List<Event> events) {
    this.events = events;

    load();
  }
  /**
   * Constructs an EventPane with the specified list of events and a specific event.
   *
   * @param events The list of events to display.
   * @param event  The specific event to highlight.
   */

  public EventPane(List<Event> events, Event event) {
    this.events = events;

    this.event = event;
  }
  /**
   * Returns the specific event that is highlighted in the EventPane.
   *
   * @return The specific event.
   */

  public Event getEvent() {
    return event;
  }

  /**
   * Loads the events into the EventPane.
   */
  private void load() {
    for (Event event : events) {
      addEvent(event);
    }
  }

  /**
   * Adds an event to the EventPane.
   *
   * @param event The event to add.
   */
  public void addEvent(Event event) {
    VBox vBox = new VBox();
    addLabel(vBox, event.name());
    addLabel(vBox, event.description());
    addLabel(vBox, event.day());
    addLabel(vBox, event.startTime());
    addLabel(vBox, String.valueOf(event.duration()));
    vBox.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: lightblue;");
    vBox.setMaxWidth(170);
    vBox.setMaxHeight(160);
    this.getChildren().add(vBox);
  }

  /**
   * Adds a label to the specified VBox with the given value.
   * The label is styled and includes a tooltip.
   *
   * @param vBox  The VBox to which the label is added.
   * @param value The value to be displayed in the label.
   */
  private void addLabel(VBox vBox, String value) {
    Label label = new Label();
    label.setStyle("-fx-font-size:14;");
    label.setText(value);
    label.setTooltip(new Tooltip(value));
    vBox.getChildren().add(label);
  }

}

