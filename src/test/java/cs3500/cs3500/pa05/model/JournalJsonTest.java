package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Constraint;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.JournalJson;
import cs3500.pa05.model.Task;
import java.util.List;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the JournalJson class
 */
class JournalJsonTest {
  private JournalJson journalJson;

  /**
   * Sets up the test ffixture before each test case
   */
  @BeforeEach
  void setUp() {
    List<Event> events = List.of();
    List<Task> tasks = List.of();
    Constraint constraint = new Constraint();
    String quote = null;
    journalJson = new JournalJson(events, tasks, constraint, quote);
  }

  /**
   * Test case for converting JournalJson to Journal.
   */
  @Test
  void toJournal() {

    Journal journal = journalJson.toJournal();

    ObservableList<Event> convertedEvents = journal.events();
    ObservableList<Task> convertedTasks = journal.tasks();
    Constraint convertedConstraint = journal.constraint();
    final String convertedQuote = journal.quote();

    assertEquals(journalJson.events(), convertedEvents);
    assertEquals(journalJson.tasks(), convertedTasks);
    assertEquals(journalJson.constraint(), convertedConstraint);
    assertEquals(journalJson.quote(), convertedQuote);
  }
}
