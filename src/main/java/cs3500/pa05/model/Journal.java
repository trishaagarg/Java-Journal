package cs3500.pa05.model;

import javafx.collections.ObservableList;

/**
 * Represents a journal containing events, tasks, constraint, and a quote.
 *
 * @param events     The list of events in the journal.
 * @param tasks      The list of tasks in the journal.
 * @param constraint The constraint of the journal.
 * @param quote      The quote of the journal.
 */
public record Journal(
    ObservableList<Event> events,
    ObservableList<Task> tasks,
    Constraint constraint, String quote) {


  /**
   * Converts the journal to a {@link JournalJson} object.
   *
   * @return The converted {@link JournalJson} object.
   */
  public JournalJson toJournalJson() {
    return new JournalJson(events, tasks, constraint, quote);
  }
}
