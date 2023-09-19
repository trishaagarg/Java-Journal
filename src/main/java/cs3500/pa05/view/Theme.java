package cs3500.pa05.view;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * Represents a theme for the application.
 */

public class Theme {
  private String name;
  private Color backgroundColor;
  private Color fontColor;
  private String fontFamily;
  private String iconPath;


  /**
   * Constructs a theme with the specified properties.
   *
   * @param name           The name of the theme.
   * @param backgroundColor The background color of the theme.
   * @param fontColor      The font color of the theme.
   * @param fontFamily     The font family of the theme.
   * @param iconPath       The path to the theme's icon.
   */
  public Theme(String name, Color backgroundColor, Color fontColor, String fontFamily,
               String iconPath) {
    this.name = name;
    this.backgroundColor = backgroundColor;
    this.fontColor = fontColor;
    this.fontFamily = fontFamily;
    this.iconPath = iconPath;
  }

  /**
   * Returns the name of the theme.
   *
   * @return The name of the theme.
   */
  public String getName() {
    return name;
  }

}