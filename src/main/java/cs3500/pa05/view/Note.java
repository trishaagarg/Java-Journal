package cs3500.pa05.view;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a note with content.
 *
 */
public class Note {
  private final SimpleStringProperty content;
  /**
   * Constructs a note with the specified content.
   *
   * @param content The content of the note.
   */

  public Note(String content) {
    this.content = new SimpleStringProperty(content);
  }


  /**
   * Returns the content of the note.
   *
   * @return The content of the note.
   */
  public String getContent() {
    return content.get();
  }

  /**
   * Returns the SimpleStringProperty that represents the content of the note.
   *
   * @return The SimpleStringProperty for the content.
   */

  public SimpleStringProperty contentProperty() {
    return content;
  }
}
