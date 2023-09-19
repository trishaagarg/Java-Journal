package cs3500.pa05.model;

import javafx.beans.property.StringProperty;


/**
 *
 * @param maxTasksPerDay The maximum number of tasks allowed per day.
 * @param maxEventsPerDay The maximum number of events allowed per day.
 */
public record AddConstraint(

    StringProperty maxTasksPerDay,

    StringProperty maxEventsPerDay) {
}

