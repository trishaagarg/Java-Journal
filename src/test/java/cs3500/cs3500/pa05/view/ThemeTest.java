package cs3500.cs3500.pa05.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.view.Theme;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the Theme class
 *
 */
public class ThemeTest {

  private Theme theme;

  /**
   *  Sets up the test fixture before each test case.
   *
   */
  @BeforeEach
  void setUp() {

    String name = "Dark Theme";
    Color backgroundColor = Color.BLACK;
    Color fontColor = Color.WHITE;
    String fontFamily = "Arial";
    String iconPath = "/path/to/icon.png";
    theme = new Theme(name, backgroundColor, fontColor, fontFamily, iconPath);
  }

  /**
   * Test getName method
   */
  @Test
  void testGetName() {

    String expectedName = "Dark Theme";
    assertEquals(expectedName, theme.getName());
  }
}
