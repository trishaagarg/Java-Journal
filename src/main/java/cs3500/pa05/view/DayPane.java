package cs3500.pa05.view;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



/**
 * Represents a pane that displays the tasks and events for a specific day in a journal.
 */
public class DayPane extends VBox {
  private String day;
  private Journal journal;
  ProgressBar progressBar;
  private Label taskCountLabel;
  private Label tasksRemainingLabel;
  private ComboBox<String> themeComboBox;
  private Label lblDay;
  private  String theme;


  /**
   * Constructs a DayPane for the specified day and journal.
   *
   * @param day     The day to display.
   * @param journal The journal containing the tasks and events.
   */
  public DayPane(String day, Journal journal) {
    this.day = day;
    this.journal = journal;
    this.theme = "";
    this.tasksRemainingLabel = new Label();
    this.lblDay = new Label(day);
    load(theme);

  }

  /**
   * Loads the content of the DayPane with the specified theme.
   *
   * @param theme The theme of the journal.
   */
  private void load(String theme) {
    final List<Event> events = this.journal.events().stream()
        .filter(p -> p.day().equals(day)).toList();
    final List<Task> tasks = this.journal.tasks().stream()
        .filter(p -> p.day().equals(day)).toList();

    this.setMaxWidth(170);
    this.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: lightgrey;");

    lblDay.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: black;");
    this.setMaxWidth(500);
    this.getChildren().add(lblDay);

    progressBar = new ProgressBar();
    taskCountLabel = new Label();
    updateProgress(tasks);

    HBox progressBox = new HBox(progressBar, taskCountLabel);
    progressBox.setSpacing(10);
    this.getChildren().add(progressBox);

    tasksRemainingLabel.setStyle("-fx-font-size: 12");
    this.getChildren().add(tasksRemainingLabel);

    setupEventPane(events);
    setupTaskPane(tasks);

    journal.tasks().addListener((ListChangeListener<Task>) c -> {
      updateProgress(journal.tasks().stream().filter(p -> p.day().equals(day)).toList());
      setTasksRemaining(countTasksRemaining(day));
    });

    setTasksRemaining(countTasksRemaining(day));
  }

  /**
   * Sets up the event pane for displaying events.
   *
   * @param events The list of events.
   */
  private void setupEventPane(List<Event> events) {
    EventPane eventPane = new EventPane(new ArrayList<>());
    if (events.size() > 0) {
      eventPane = new EventPane(events);
    }
    eventPane.setId("eventPane_" + day);

    this.getChildren().add(eventPane);

    this.journal.events().addListener((ListChangeListener<Event>) c -> {
      c.next();
      List<? extends Event> addedSubList = c.getAddedSubList();
      Event event = addedSubList.get(0);
      Optional<Node> first = getChildren().stream()
          .filter(p -> Objects.equals(p.getId(), "eventPane_" + event.day())).findFirst();
      if (first.isPresent()) {
        EventPane changedEventPane = (EventPane) first.get();
        changedEventPane.addEvent(event);
      }
    });
  }

  /**
   * Sets up the task pane for displaying tasks.
   *
   * @param tasks The list of tasks.
   */
  private void setupTaskPane(List<Task> tasks) {
    TaskPane taskPane = new TaskPane(new ArrayList<>());
    if (tasks.size() > 0) {
      taskPane = new TaskPane(tasks);
    }
    taskPane.setId("taskPane_" + day);
    this.getChildren().add(taskPane);

    this.journal.tasks().addListener((ListChangeListener<Task>) c -> {
      c.next();
      List<? extends Task> addedSubList = c.getAddedSubList();
      Task task = addedSubList.get(0);
      Optional<Node> first = getChildren().stream()
          .filter(p -> Objects.equals(p.getId(), "taskPane_" + task.day())).findFirst();
      if (first.isPresent()) {
        TaskPane changedTaskPane = (TaskPane) first.get();
        changedTaskPane.addTask(task);
      }
    });
  }

  /**
   * Updates the progress bar and task count label based on the given list of tasks.
   *
   * @param tasks The list of tasks.
   */

  void updateProgress(List<Task> tasks) {
    int totalTasks = tasks.size();
    int completedTasks = (int) tasks.stream().filter(Task::isCompleted).count();
    double progress = (totalTasks > 0) ? (completedTasks / (double) totalTasks) : 0;

    progressBar.setProgress(progress);
    taskCountLabel.setText(completedTasks + "/" + totalTasks);
  }

  /**
   * Counts the number of tasks remaining for the specified day based on the journal's constraints.
   *
   * @param day The day for which to count the tasks remaining.
   * @return The number of tasks remaining for the day.
   */
  private int countTasksRemaining(String day) {
    List<Task> tasks = this.journal.tasks().stream().filter(p -> p.day().equals(day)).toList();
    String maxTasksPerDayString = this.journal.constraint().getMaxTasksPerDay();
    int maxTasksPerDay = (maxTasksPerDayString != null)
        ? Integer.parseInt(maxTasksPerDayString) : 0;
    int completedTasks = (int) tasks.stream().filter(Task::isCompleted).count();
    return maxTasksPerDay - completedTasks;
  }


  /**
   * Sets the label text for displaying the number of tasks remaining.
   *
   * @param tasksRemaining The number of tasks remaining.
   */
  public void setTasksRemaining(int tasksRemaining) {
    tasksRemainingLabel.setText("Tasks Remaining: " + tasksRemaining);
  }

}
