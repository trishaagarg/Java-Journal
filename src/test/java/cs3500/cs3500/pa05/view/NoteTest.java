package cs3500.cs3500.pa05.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.view.Note;
import javafx.beans.property.SimpleStringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for the Note class.
 */

public class NoteTest {
  private Note note;

  /**
   * Sets up the test fixture before each test case.
   */
  @BeforeEach
  void setUp() {

    String content = "Sample note content";
    note = new Note(content);
  }

  /**
   * Test case for getting the content of the note.
   */
  @Test
  void testGetContent() {

    String expectedContent = "Sample note content";
    assertEquals(expectedContent, note.getContent());
  }
  /**
   * Test case for accessing the content property of the note.
   */

  @Test
  void testContentProperty() {
    assertEquals(SimpleStringProperty.class, note.contentProperty().getClass());
  }
}
