package cs3500.pa05.view;

import cs3500.pa05.Driver;
import cs3500.pa05.controller.JournalService;
import cs3500.pa05.model.AddEvent;
import cs3500.pa05.model.AddTask;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


/**
 * Controller class for the Bullet Journal application.
 */
public class BulletJournalController implements Initializable {
  @FXML
  public Button addTask;
  @FXML
  public Button save;
  @FXML
  private Label quotesOrNotes;
  @FXML
  private Label theme;
  @FXML
  public Button setConstraint;
  @FXML
  public Button open;
  @FXML
  public Button addEvent;
  @FXML
  private VBox testing1;
  @FXML
  ScrollPane scrollPaneDown;
  @FXML
  private VBox stackvd;
  @FXML
  private HBox hBoxHead;
  @FXML
  private ScrollPane scrollPane;

  @FXML
  private HBox hBoxContent;

  // Sidebar elements
  @FXML
  private Label lblMaxTasksPerDay;

  @FXML
  private Label lblMaxEventsPerDay;

  @FXML
  private Label lblStatistics;

  @FXML
  private TextArea txtQuoteOrNote;

  @FXML
  private Label lblSavedQuote;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button switchLayoutButton;


  private Label weekLabel = new Label("Week Name : College");
  private boolean isHorizontalLayout = true;

  @FXML
  public ComboBox<String> themeComboBox;

  @FXML
  private Stage stage;

  @FXML
  private TextField searchField;

  @FXML
  private ListView<Task> taskListView;

  private ObservableList<Task> allTasks;

  @FXML
  private VBox root;
  Journal journal;
  JournalService journalService;
  private String templateFilePath;

  private ObservableList<String> themes = FXCollections.observableArrayList(
      "Light Theme",
      "Dark Theme",
      "Custom Theme"
  );
  private int maxEventsPerDay;
  private int maxTasksPerDay;

  /**
   * Initializes the controller.
   *
   * @param location  The location used to resolve relative paths for the root object,
   *                  or null if the location is not known.
   * @param resources The resources used to localize the root object,
   *                  or null if the root object was not localized.
   */

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    journalService = new JournalService();
    showWelcomeScreen();

    allTasks = FXCollections.observableArrayList(journal.tasks());

    setupSearchListener();

    themes = FXCollections.observableArrayList(
        "Pink Theme",
        "Dark Theme",
        "Light Theme",
        "Custom Theme"
    );
    themeComboBox.setItems(themes);

    themeComboBox.setOnAction(event -> {
      String selectedTheme = themeComboBox.getValue();
      if (selectedTheme.equals("Custom Theme")) {
        onCustomThemeSelected(event);
        open.setStyle(
            "-fx-background-radius: 60; -fx-min-width: 100px; -fx-min-height: 100px; "
                + "-fx-max-width: 110px; -fx-max-height: 110px; "
                + "-fx-background-image: url('/mush.PNG'); -fx-background-size:110px; "
                + "-fx-background-color:#FFFFFF");
        save.setStyle(
            "-fx-background-radius: 60; -fx-min-width: 100px; -fx-min-height: 100px; "
                + "-fx-max-width: 110px; -fx-max-height: 110px; "
                + "-fx-background-image: url('/frog.PNG'); -fx-background-size:110px; "
                + "-fx-background-color:#FFFFFF");
        addTask.setStyle(
            "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
                + "-fx-max-width: 110px; -fx-max-height: 110px; "
                + "-fx-background-image: url('/frog.PNG'); -fx-background-size:110px; "
                + "-fx-background-color:#FFFFFF");
        addEvent.setStyle(
            "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
                + "-fx-max-width: 110px; -fx-max-height: 110px; "
                + "-fx-background-image: url('/star.PNG'); -fx-background-size:110px; "
                + "-fx-background-color:#FFFFFF");
        setConstraint.setStyle(
            "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
                + "-fx-max-width: 110px; -fx-max-height: 110px; "
                + "-fx-background-image: url('/dog.PNG'); -fx-background-size:110px; "
                + "-fx-background-color:#FFFFFF");
        // Update font family
      } else {
        changeTheme(selectedTheme);
      }
    });

    String filePath = "";
    createMenuBar(filePath);
  }


  /**
   * Shows the welcome screen to the user.
   */

  private void showWelcomeScreen() {
    Alert welcomeAlert = new Alert(Alert.AlertType.INFORMATION);
    welcomeAlert.setTitle("Welcome to Bullet Journal");
    welcomeAlert.setHeaderText("Welcome!");

    // Load the image using getResource()
    Image image = new Image(getClass().getResource("/welcome.jpg").toString());

    // Create an ImageView with the loaded image
    ImageView imageView = new ImageView(image);
    double desiredWidth = 500;
    double desiredHeight = 500;
    imageView.setFitWidth(desiredWidth);
    imageView.setFitHeight(desiredHeight);

    // Set the ImageView as the graphic for the alert dialog
    welcomeAlert.getDialogPane().setContent(imageView);

    // Set the content text and wait for user interaction
    welcomeAlert.setContentText("Click 'OK' to continue or wait for 2 seconds.");
    Optional<ButtonType> result = showAndWaitWithTimeout(welcomeAlert, 2000);
    String filePath = "";

    if (result.isPresent() && result.get() == ButtonType.OK) {
      openBujoFile();
    } else {
      openBujoFile();
    }
  }

  /**
   * Shows an alert dialog and waits for a specified timeout period.
   *
   * @param alert         The alert dialog to be shown.
   * @param timeoutMillis The timeout period in milliseconds.
   * @return An Optional object representing the user's response to the dialog,
   * or an empty Optional if the dialog was closed due to timeout.
   */

  private Optional<ButtonType> showAndWaitWithTimeout(Alert alert, int timeoutMillis) {
    Thread closeDialogThread = new Thread(() -> {
      try {
        Thread.sleep(2000);
        Platform.runLater(alert::close);
      } catch (InterruptedException e) {
        // Ignore interruption
      }
    });

    closeDialogThread.start();
    return alert.showAndWait();
  }


  /**
   * Creates the menu bar with file commands.
   *
   * @param fileName The name of the file.
   */
  private void createMenuBar(String fileName) {
    final MenuBar menuBar = new MenuBar();

    // File Menu
    final Menu fileMenu = new Menu("File Commands");

    MenuItem newEventMenuItem = new MenuItem("Create New Event");
    newEventMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+E"));
    newEventMenuItem.setOnAction(event -> {
      onAddEventClick();
    });

    MenuItem newTaskMenuItem = new MenuItem("Create New Task");
    newTaskMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+T"));
    newTaskMenuItem.setOnAction(event -> {
      onAddTaskClick();
    });

    MenuItem saveMenuItem = new MenuItem("Save");
    saveMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+S"));
    saveMenuItem.setOnAction(event -> {
      onSaveJournalClick(event);
    });

    MenuItem openMenuItem = new MenuItem("Open");
    openMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+O"));
    openMenuItem.setOnAction(event -> {
      onLoadJournalClick(event);
    });

    MenuItem newWeekMenuItem = new MenuItem("New Week");
    newWeekMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+N"));
    newWeekMenuItem.setOnAction(event -> {

      String weekName = "";
      createNewWeekView(fileName);
    });

    MenuItem setMaxEventsMenuItem = new MenuItem("Set Max Events");
    setMaxEventsMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+M"));
    setMaxEventsMenuItem.setOnAction(event -> {
      setMaxEventsPerDay(maxEventsPerDay);
    });
    MenuItem setMaxTasksMenuItem = new MenuItem("Set Max Tasks");
    setMaxTasksMenuItem.setAccelerator(KeyCombination.keyCombination("Shortcut+W"));
    setMaxTasksMenuItem.setOnAction(event -> {
      setMaxTasksPerDay(maxTasksPerDay);
    });
    fileMenu.getItems().addAll(newEventMenuItem, newTaskMenuItem, saveMenuItem, openMenuItem,
        newWeekMenuItem, setMaxEventsMenuItem, setMaxTasksMenuItem);


    menuBar.getMenus().addAll(fileMenu);
    // Add the menu bar to your layout, such as hBoxContent or a new container pane
    hBoxContent.getChildren().add(menuBar);
  }

  /**
   * Sets the maximum number of events per day.
   *
   * @param maxEventsPerDay The maximum number of events per day.
   */

  private void setMaxEventsPerDay(int maxEventsPerDay) {
    String input = showInputDialog("Enter the maximum number of events per day:");
    try {
      int value = Integer.parseInt(input);
      if (value >= 0) {
        this.maxEventsPerDay = value;
        lblMaxEventsPerDay.setText("Max Events Per Day: " + this.maxEventsPerDay);
        journal.constraint().setMaxEventsPerDay(String.valueOf(this.maxEventsPerDay));
      } else {
        showAlertMessage("Invalid input. Please enter a non-negative integer.");
      }
    } catch (NumberFormatException e) {
      showAlertMessage("Invalid input. Please enter a valid integer.");
    }
  }

  /**
   * Sets the maximum number of tasks per day.
   *
   * @param maxTasksPerDay The maximum number of tasks per day.
   */
  private void setMaxTasksPerDay(int maxTasksPerDay) {
    String input = showInputDialog("Enter the maximum number of tasks per day:");
    try {
      int value = Integer.parseInt(input);
      if (value >= 0) {
        this.maxTasksPerDay = value;
        lblMaxTasksPerDay.setText("Max Tasks Per Day: " + this.maxTasksPerDay);
        journal.constraint().setMaxTasksPerDay(String.valueOf(this.maxTasksPerDay));
      } else {
        showAlertMessage("Invalid input. Please enter a non-negative integer.");
      }
    } catch (NumberFormatException e) {
      showAlertMessage("Invalid input. Please enter a valid integer.");
    }
  }

  /**
   * Sets up a listener for the search field to filter tasks based on the search term.
   */
  private void setupSearchListener() {
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
      filterTasks(newValue);
    });
  }


  /**
   * Filters tasks based on the provided search term and updates the task list view.
   *
   * @param searchTerm The search term used to filter tasks.
   */
  private void filterTasks(String searchTerm) {
    taskListView.getItems().clear();

    if (searchTerm.isEmpty()) {
      taskListView.getItems().addAll(allTasks);
      return;
    }

    List<Task> filteredTasks = allTasks.stream()
        .filter(task -> task.name().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

    taskListView.getItems().addAll(filteredTasks);
  }


  /**
   * Opens a Bujo file using a file chooser dialog.
   */
  @FXML
  private void openBujoFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Bujo Files", "*.bujo"));
    File selectedFile = fileChooser.showOpenDialog(stage);

    if (selectedFile != null) {
      String filePath = selectedFile.getPath();
      loadView(filePath);
    }
  }

  /**
   * Loads the Bujo view based on the provided file name.
   *
   * @param fileName The name of the Bujo file to load.
   * @throws IOException If an error occurs while loading the Bujo file.
   */
  private void loadView(String fileName) {
    boolean isAuthenticated = authenticateUser();
    if (!isAuthenticated) {
      return; // Exit method if authentication fails
    }
    final String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday"};
    try {
      this.journal = journalService.loadJournal(fileName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    hBoxContent.getChildren().clear();
    createMenuBar(fileName);
    String weekName = "";

    hBoxContent.getChildren().add(weekLabel);
    weekLabel.setStyle("-fx-font-size: 18px;");
    for (String day : days) {
      DayPane dayPane = new DayPane(day, this.journal);
      int tasksRemaining = countTasksRemaining(day);
      dayPane.setTasksRemaining(tasksRemaining);

      hBoxContent.getChildren().add(dayPane);

    }

    updateStatistics();
  }

  /**
   * Authenticates the user by requesting a password input via a dialog.
   *
   * @return {@code true} if the authentication is successful, {@code false} otherwise.
   */
  private boolean authenticateUser() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Authentication Required");
    dialog.setHeaderText("Please enter your password:");
    dialog.setContentText("Password:");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent() && result.get().equals("OOD")) {
      return true; // Authentication successful
    } else {
      showAlertMessage("Incorrect password. Access denied.");
      return false; // Authentication failed
    }
  }

  /**
   * Counts the number of tasks remaining for the given day based on the maximum tasks
   * per day constraint.
   *
   * @param day The day for which to count the tasks remaining.
   * @return The number of tasks remaining for the day.
   */
  private int countTasksRemaining(String day) {
    int maxTasksPerDay = 0;
    if (this.journal.constraint().getMaxTasksPerDay() != null) {
      try {
        maxTasksPerDay = Integer.parseInt(this.journal.constraint().getMaxTasksPerDay());
      } catch (NumberFormatException e) {
        showAlertMessage("Invalid maximum tasks per day value. Please enter a valid integer.");
      }
    }

    int tasksCount = (int) this.journal.tasks().stream().filter(p -> p.day().equals(day)).count();
    return maxTasksPerDay - tasksCount;
  }


  /**
   * Handles the "Save Quote or Note" button click event.
   *
   * @param event The action event triggered by the button click.
   */
  @FXML
  void onSaveQuoteOrNoteClick(ActionEvent event) {
    String quoteOrNote = txtQuoteOrNote.getText();
    lblSavedQuote.setText(quoteOrNote);
    txtQuoteOrNote.clear();
  }

  /**
   * Handles the "Add Event" button click event.
   *
   * @throws IOException If an error occurs while adding the event.
   */

  @FXML
  void onAddEventClick() {
    FXMLLoader fxmlLoader =
        new FXMLLoader(Driver.class.getResource("/add-event-dialog.fxml"));
    DialogPane dialogPane = null;
    try {
      dialogPane = fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    AddEventController addEventController = fxmlLoader.getController();
    AddEvent addEvent = new AddEvent(new SimpleStringProperty(),
        new SimpleStringProperty(), new SimpleStringProperty(),
        new SimpleStringProperty(), new SimpleStringProperty());
    addEventController.setEvent(addEvent);

    dialogPane.getButtonTypes().add(ButtonType.OK);
    dialogPane.getButtonTypes().add(ButtonType.CANCEL);
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setDialogPane(dialogPane);
    Optional<ButtonType> clickedButton = dialog.showAndWait();
    if (clickedButton.get() == ButtonType.OK) {
      Event eventObj = addEvent.toEvent();
      int eventsCount =
          (int) this.journal.events().stream().filter(p -> p.day().equals(eventObj.day())).count();
      if (eventsCount == Integer.parseInt(this.journal.constraint().getMaxEventsPerDay())) {

        showAlertMessage("Your day is full for the events!!!");
      } else {
        this.journal.events().add(eventObj);
        updateStatistics();
      }
    }
  }

  /**
   * Counts the number of events remaining for a given day.
   *
   * @param day The day for which to count the events remaining.
   * @return The number of events remaining for the given day.
   */
  private int countEventsRemaining(String day) {
    int eventsCount = (int) this.journal.events().stream().filter(p -> p.day().equals(day)).count();
    return maxEventsPerDay - eventsCount;
  }

  /**
   * Handles the click event of the "Add Task" button.
   */
  @FXML
  void onAddTaskClick() {

    FXMLLoader fxmlLoader =
        new FXMLLoader(Driver.class.getResource("/add-task-dialog.fxml"));
    DialogPane dialogPane = null;
    try {
      dialogPane = fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    AddTaskController addTaskController = fxmlLoader.getController();
    AddTask addTask = new AddTask(
        new SimpleStringProperty(),
        new SimpleStringProperty(),
        new SimpleStringProperty(),
        new SimpleBooleanProperty(false)
    );
    addTaskController.setTask(addTask);
    dialogPane.getButtonTypes().add(ButtonType.OK);
    dialogPane.getButtonTypes().add(ButtonType.CANCEL);
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setDialogPane(dialogPane);
    Optional<ButtonType> clickedButton = dialog.showAndWait();
    if (clickedButton.get() == ButtonType.OK) {
      Task taskObj = addTask.toTask();


      int tasksCount =
          (int) this.journal.tasks().stream().filter(p -> p.day().equals(taskObj.day())).count();
      if (tasksCount == Integer.parseInt(this.journal.constraint().getMaxTasksPerDay())) {
        showAlertMessage("Your day is full for the tasks!!!");
      } else {
        this.journal.tasks().add(taskObj);
        allTasks.add(taskObj);
        updateStatistics();
        filterTasks(searchField.getText());
      }
    }
  }


  /**
   * Handles the click event of the "Load Journal" button.
   *
   * @param event The action event triggered by the button click.
   */

  @FXML
  void onLoadJournalClick(ActionEvent event) {
    String fileName = showInputDialog("File Name: ");
    loadView(fileName);


  }

  /**
   * Handles the click event of the "Save Journal" button.
   *
   * @param event The action event triggered by the button click.
   */

  @FXML
  void onSaveJournalClick(ActionEvent event) {
    String fileName = showInputDialog("File Name: ");
    try {
      this.journalService.saveJournal(fileName, journal);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    showAlertMessage("Journal successfully persisted.");
  }

  /**
   * Updates the statistics displayed in the user interface.
   */

  private void updateStatistics() {
    int totalTasks = this.journal.tasks().size();
    int completedTasks = (int) this.journal.tasks().stream().filter(Task::isCompleted).count();
    double taskCompletionPercentage =
        (totalTasks > 0) ? (completedTasks / (double) totalTasks) * 100 : 0;

    lblStatistics.setText(
        String.format("Total Events: %d\nTotal Tasks: %d\nTask Completion: %.2f%%",
            this.journal.events().size(), totalTasks, taskCompletionPercentage));
  }

  /**
   * Displays an alert message dialog with the given message.
   *
   * @param message The message to display in the alert dialog.
   */

  private void showAlertMessage(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Displays an input dialog and returns the user's input.
   *
   * @param message The message to display in the input dialog.
   * @return The user's input as a string.
   */

  private String showInputDialog(String message) {
    TextInputDialog dialog = new TextInputDialog("");
    dialog.setContentText(message);
    Optional<String> result = dialog.showAndWait();
    return result.orElse("");
  }

  /**
   * Changes the theme of the application based on the provided theme string.
   *
   * @param theme The theme to apply to the application.
   * @return The applied theme.
   */

  public String changeTheme(String theme) {
    if (theme.equals("Pink Theme")) {
      // Set light theme colors, font, and icons
      root.setStyle("-fx-background-color: #FFDBFC;");
      taskListView.setStyle("-fx-background-color: #FFDBE9;");
      lblSavedQuote.setStyle("-fx-background-color: #FFDBE3;");
      scrollPaneDown.setStyle("-fx-background-color: #FFC0CB;");
      txtQuoteOrNote.setStyle("-fx-background-color: #FFC0CB;");
      hBoxHead.setStyle("-fx-background-color: #FFE0DB;");
      hBoxContent.setStyle("-fx-background-color: #FFDBDD;");
      stackvd.setStyle("-fx-background-color: #FFE0DB;");
      testing1.setStyle("-fx-background-color: #FFF2DB;");
      stackvd.setStyle("-fx-background-image: url('/pinktheme.jpg');");
      open.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/testingimage.png'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      save.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/testingimage.png'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      addTask.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/testingimage.png'); "
              + "-fx-background-size:110px; -fx-background-color:#FFFFFF");
      addEvent.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/testingimage.png'); "
              + "-fx-background-size:110px; -fx-background-color:#FFFFFF");
      setConstraint.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/testingimage.png'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      // Update font color
      lblMaxTasksPerDay.setTextFill(Color.RED);
      lblMaxEventsPerDay.setTextFill(Color.RED);
      lblStatistics.setTextFill(Color.RED);
      quotesOrNotes.setTextFill(Color.RED);
      setConstraint.setTextFill(Color.RED);
      addEvent.setTextFill(Color.RED);
      addTask.setTextFill(Color.RED);
      open.setTextFill(Color.RED);
      save.setTextFill(Color.RED);
      // Update font family
      Font font = Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 15);
      lblMaxTasksPerDay.setFont(font);
      lblMaxEventsPerDay.setFont(font);
      lblStatistics.setFont(font);
      quotesOrNotes.setFont(font);
      Font font1 = Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 12);

      // Update icons
      open.setFont(font1);
      save.setFont(font1);
      addEvent.setFont(font1);
      addTask.setFont(font1);
      Font font2 = Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 10);
      setConstraint.setFont(font2);

    } else if (theme.equals("Dark Theme")) {
      // Set dark theme colors, font, and icons

      root.setStyle("-fx-background-color: #3d3d3d;");
      taskListView.setStyle("-fx-background-color: #A9A9A9;");
      lblSavedQuote.setStyle("-fx-background-color: #A9A9A9;");
      scrollPaneDown.setStyle("-fx-background-color: #A9A9A9;");
      txtQuoteOrNote.setStyle("-fx-background-color: #A9A9A9;");
      hBoxHead.setStyle("-fx-background-color: #A9A9A9;");
      hBoxContent.setStyle("-fx-background-color: #A9A9A9;");
      stackvd.setStyle("-fx-background-color: #A9A9A9;");
      testing1.setStyle("-fx-background-color: #A9A9A9;");

      // Update font color
      lblMaxTasksPerDay.setTextFill(Color.PINK);
      lblMaxEventsPerDay.setTextFill(Color.PINK);
      lblStatistics.setTextFill(Color.PINK);
      quotesOrNotes.setTextFill(Color.PINK);
      setConstraint.setTextFill(Color.BLACK);
      addEvent.setTextFill(Color.BLACK);
      addTask.setTextFill(Color.BLACK);
      open.setTextFill(Color.BLACK);
      save.setTextFill(Color.BLACK);
      stackvd.setStyle("-fx-background-image: url('/sleepkola.jpg')");
      open.setStyle(
          "-fx-background-radius: 60; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/kola.PNG'); "
              + "-fx-background-size:110px; -fx-background-color:#FFFFFF");
      save.setStyle(
          "-fx-background-radius: 60; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/kola.PNG'); "
              + "-fx-background-size:110px; -fx-background-color:#FFFFFF");
      addTask.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/kola.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      addEvent.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/kola.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      setConstraint.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/kola.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      // Update font family
      Font font = Font.font("Gill Sans", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 14);
      lblMaxTasksPerDay.setFont(font);
      lblMaxEventsPerDay.setFont(font);
      lblStatistics.setFont(font);
      quotesOrNotes.setFont(font);

      addEvent.setFont(font);
      addTask.setFont(font);

      Font font1 = Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 12);
      open.setFont(font1);
      save.setFont(font1);
      addEvent.setFont(font1);
      addTask.setFont(font1);
      Font font2 = Font.font("Georgia", FontWeight.BOLD, FontPosture.ITALIC, 10);
      setConstraint.setFont(font2);
      // Update icons
      // ...
    } else if (theme.equals("Light Theme")) {
      root.setStyle("-fx-background-color: #FFFFFF;");
      taskListView.setStyle("-fx-background-color: #FFFFFF;");
      lblSavedQuote.setStyle("-fx-background-color: #FFFFFF;");
      scrollPaneDown.setStyle("-fx-background-color: #FFFFFF;");
      txtQuoteOrNote.setStyle("-fx-background-color: #FFFFFF;");
      hBoxHead.setStyle("-fx-background-color: #FFFFFF;");
      hBoxContent.setStyle("-fx-background-color: #FFFFFF;");
      stackvd.setStyle("-fx-background-color: #FFFFFF;");
      testing1.setStyle("-fx-background-color: #FFFFFF;");
      // Update font color
      stackvd.setStyle("-fx-background-image: url('/cat.PNG')");
      open.setStyle(
          "-fx-background-radius: 60; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/mush.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      save.setStyle(
          "-fx-background-radius: 60; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/mush.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      addTask.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/mush.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      addEvent.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/mush.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      setConstraint.setStyle(
          "-fx-background-radius: 50; -fx-min-width: 100px; -fx-min-height: 100px; "
              + "-fx-max-width: 110px; -fx-max-height: 110px; "
              + "-fx-background-image: url('/mush.PNG'); -fx-background-size:110px; "
              + "-fx-background-color:#FFFFFF");
      // Update font family
      lblMaxTasksPerDay.setTextFill(Color.BLACK);
      lblMaxEventsPerDay.setTextFill(Color.BLACK);
      lblStatistics.setTextFill(Color.BLACK);
      quotesOrNotes.setTextFill(Color.BLACK);
      setConstraint.setTextFill(Color.BLACK);
      addEvent.setTextFill(Color.BLACK);
      addTask.setTextFill(Color.BLACK);
      open.setTextFill(Color.BLACK);
      save.setTextFill(Color.BLACK);

      // Update font family
      Font font = Font.font("Ariel", FontWeight.NORMAL, FontPosture.ITALIC, 12);
      lblMaxTasksPerDay.setFont(font);
      lblMaxEventsPerDay.setFont(font);
      lblStatistics.setFont(font);
      quotesOrNotes.setFont(font);

      // change the icons
    }
    return theme;
  }

  /**
   * Handles the event when a custom theme is selected.
   *
   * @param event The event triggered by selecting a custom theme.
   */
  @FXML
  private void onCustomThemeSelected(ActionEvent event) {
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(e -> {
      Color color = colorPicker.getValue();
      // Set the background color of the Week view
      hBoxContent.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
    });

    Dialog<Color> dialog = new Dialog<>();
    dialog.setTitle("Custom Theme - Background Color");
    dialog.getDialogPane().setContent(colorPicker);
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    dialog.setResultConverter(buttonType -> {
      if (buttonType == ButtonType.OK) {
        return colorPicker.getValue();
      }
      return null;
    });
    Optional<Color> result = dialog.showAndWait();
    result.ifPresent(color -> {
      // Apply the selected color to the Week view

      root.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      taskListView.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      lblSavedQuote.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      scrollPaneDown.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      txtQuoteOrNote.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      hBoxHead.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      hBoxContent.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      stackvd.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
      testing1.setStyle("-fx-background-color: " + toRGBCode(color) + ";");
    });
  }

  /**
   * Helper method to convert a Color object to an RGB code string.
   *
   * @param color The Color object to convert.
   * @return The RGB code string representing the color.
   */
  private String toRGBCode(Color color) {
    return String.format("#%02X%02X%02X",
        (int) (color.getRed() * 255),
        (int) (color.getGreen() * 255),
        (int) (color.getBlue() * 255));
  }

  /**
   * Handles the event when the "Set Constraint" button is clicked.
   *
   * @param event The event triggered by clicking the "Set Constraint" button.
   */
  @FXML
  private void onSetConstraintClick(ActionEvent event) {
    Dialog<Boolean> dialog = new Dialog<>();
    dialog.setTitle("Set Constraint");
    dialog.setHeaderText("Set maximum tasks and events per day");

    DialogPane dialogPane = dialog.getDialogPane();
    dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    VBox content = new VBox();
    content.setSpacing(10);

    TextField maxTasksField = new TextField();
    maxTasksField.setPromptText("Maximum tasks per day");
    maxTasksField.setText(String.valueOf(maxTasksPerDay));

    TextField maxEventsField = new TextField();
    maxEventsField.setPromptText("Maximum events per day");
    maxEventsField.setText(String.valueOf(maxEventsPerDay));

    content.getChildren().addAll(
        new Label("Maximum tasks per day:"),
        maxTasksField,
        new Label("Maximum events per day:"),
        maxEventsField
    );

    dialogPane.setContent(content);

    Platform.runLater(maxTasksField::requestFocus);

    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == ButtonType.OK) {
        try {
          maxTasksPerDay = Integer.parseInt(maxTasksField.getText());
          maxEventsPerDay = Integer.parseInt(maxEventsField.getText());
          lblMaxTasksPerDay.setText("Max Tasks Per Day: " + maxTasksPerDay);
          lblMaxEventsPerDay.setText("Max Events Per Day: " + maxEventsPerDay);
          journal.constraint().setMaxTasksPerDay(String.valueOf(maxTasksPerDay));
          journal.constraint().setMaxEventsPerDay(String.valueOf(maxEventsPerDay));
          return true;
        } catch (NumberFormatException e) {
          showAlertMessage("Invalid input. Please enter valid integers.");
          return false;
        }
      }
      return false;
    });

    Optional<Boolean> result = dialog.showAndWait();
    result.ifPresent(constraintSet -> {
      if (constraintSet) {

        updateStatistics();
      }
    });
  }

  /**
   * Creates a new Week view using the specified file.
   *
   * @param file The file to load the Week view from.
   * @throws IOException If an error occurs while loading the file.
   */
  private void createNewWeekView(String file) {
    hBoxContent.getChildren().clear();

    loadView(file);

  }

  /**
   * Handles the event when the "Open Template" button is clicked.
   *
   * @param event The event triggered by clicking the "Open Template" button.
   */
  @FXML
  void openTemplateButtonClick(ActionEvent event) {
    String defaultFile = "/template.bujo";
    loadTemplate(defaultFile);
  }

  /**
   * Loads a template using the specified file.
   *
   * @param selectedFile The file to load the template from.
   */
  private void loadTemplate(String selectedFile) {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("New Week Name");
    dialog.setHeaderText("Enter the new week name:");
    dialog.setContentText("Week Name:");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
      String weekName = result.get();
      weekLabel.setText("" + weekName);

      changeTheme("Pink Theme");
      String defaultFile = "template.bujo";
      createNewWeekView(defaultFile);

    }
  }

}