package cs3500.cs3500.pa05.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.controller.JournalService;
import cs3500.pa05.model.Constraint;
import cs3500.pa05.model.Journal;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Test class for the JournalService class.
 */

public class JournalServiceTest {
  private JournalService journalService;
  private String testFileName;

  /**
   * setuping the test enviroment
   */
  @BeforeEach
  public void setUp() {
    journalService = new JournalService();
    testFileName = "test_journal.bujo";
  }

  /**
   * Test case for loading an existing journal from a file.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Test
  public void testLoadJournal_existingFile() throws IOException {

    File tempFile = File.createTempFile("existing_journal", ".bujo");
    Journal testJournal = new Journal(
        FXCollections.observableArrayList(),
        FXCollections.observableArrayList(),
        new Constraint(), ""
    );
    journalService.saveJournal(tempFile.getPath(), testJournal);

    Journal loadedJournal = journalService.loadJournal(tempFile.getPath());

    assertNotNull(loadedJournal);



  }

  /**
   * Test case for loading a non-existing journal file.
   *
   * @throws IOException If an I/O error occurs.
   */
  @Test
  public void testLoadJournal_nonExistingFile() throws IOException {

    File testFile = new File(testFileName);
    if (testFile.exists()) {
      assertTrue(testFile.delete());
    }

    Journal loadedJournal = journalService.loadJournal(testFileName);

    assertNotNull(loadedJournal);

    assertTrue(loadedJournal.events().isEmpty());
    assertTrue(loadedJournal.tasks().isEmpty());
    assertEquals(loadedJournal.constraint(), loadedJournal.constraint());
  }

}