package cs3500.pa05.controller;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cs3500.pa05.model.Constraint;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.JournalJson;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;


/**
 * A service class for loading and saving journal data.
 */

public class JournalService {
  private String defaultFile =
      "journal1.bujo";

  /**
   * Loads a journal from the specified file.
   *
   * @param fileName the name of the file to load the journal from
   * @return the loaded journal
   * @throws IOException if an I/O error occurs while reading the file
   */
  public Journal loadJournal(String fileName) throws IOException {
    File file = new File(fileName.isEmpty() ? defaultFile : fileName);
    Journal journal;
    if (file.exists() && file.length() > 0) {
      ObjectMapper mapper = new ObjectMapper();
      JournalJson journalJson = mapper.readValue(file, JournalJson.class);
      journal = journalJson.toJournal();
    } else {
      journal = new Journal(
          FXCollections.observableArrayList(),
          FXCollections.observableArrayList(),
          new Constraint(),
          "");
      saveJournal(fileName, journal); // Save the new journal to the file
    }
    return journal;
  }

  /**
   * Saves the specified journal to a file.
   *
   * @param fileName the name of the file to save the journal to
   * @param journal the journal to save
   * @throws IOException if an I/O error occurs while writing the file
   */

  public void saveJournal(String fileName, Journal journal) throws IOException {
    File file = new File(fileName);
    JournalJson journalJson = new JournalJson(
        journal.events(),
        journal.tasks(),
        journal.constraint(),
        journal.quote()
    );
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    writer.writeValue(file, journalJson);
  }
}


