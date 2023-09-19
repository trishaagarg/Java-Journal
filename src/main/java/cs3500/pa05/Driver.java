package cs3500.pa05;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents bullet journal application.
 */
public class Driver extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Driver.class.getResource(
        "/main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 800, 800);
    stage.setTitle("Bullet Journal");
    stage.setScene(scene);
    stage.setMinHeight(800);
    stage.setMinWidth(1000);
    stage.show();
  }

  /**
   * Runs bullet journal application.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    launch();
  }
}