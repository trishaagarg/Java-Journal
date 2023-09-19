package cs3500.cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Constraint;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.JournalJson;
import cs3500.pa05.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Journal class.
 */
public class JournalTest {
  private Journal journal;

  /**
   * Sets up the test fixture before each test case.
   */
  @BeforeEach
  void setUp() {
    ObservableList<Event> events = FXCollections.observableArrayList();
    ObservableList<Task> tasks = FXCollections.observableArrayList();
    Constraint constraint = new Constraint();
    String quote = null;
    journal = new Journal(events, tasks, constraint, quote);
  }
  /**
   * Test case for converting Journal to JournalJson.
   */

  @Test
  void toJournalJson() {

    JournalJson journalJson = journal.toJournalJson();

    ObservableList<Event> jsonEvents = (ObservableList<Event>) journalJson.events();
    ObservableList<Task> jsonTasks = (ObservableList<Task>) journalJson.tasks();
    Constraint jsonConstraint = journalJson.constraint();
    final String jsonQuote = journalJson.quote();

    assertEquals(journal.events(), jsonEvents);
    assertEquals(journal.tasks(), jsonTasks);
    assertEquals(journal.constraint(), jsonConstraint);
    assertEquals(journal.quote(), jsonQuote);
  }
}

