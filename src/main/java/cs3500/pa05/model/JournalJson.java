package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javafx.collections.FXCollections;

/**
 * Represents a JSON representation of a journal containing events, tasks, constraint, and a quote.
 *
 * @param events     The list of events in the journal.
 * @param tasks      The list of tasks in the journal.
 * @param constraint The constraint of the journal.
 * @param quote      The quote of the journal.
 */

public record JournalJson(
    @JsonProperty("events") List<Event> events,
    @JsonProperty("tasks") List<Task> tasks,
    @JsonProperty("constraints") Constraint constraint,
    @JsonProperty("quotes") String quote) {


  /**
   * Converts the JSON representation of the journal to a {@link Journal} object.
   *
   * @return The converted {@link Journal} object.
   */
  public Journal toJournal() {
    return new Journal(FXCollections.observableList(events),
        FXCollections.observableList(tasks),
        constraint, quote);
  }
}