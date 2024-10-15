package pair.task.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PairProperties {

    private final SimpleStringProperty firstEmployee;
    private final SimpleStringProperty secondEmployee;
    private final SimpleStringProperty projectId;
    private final SimpleIntegerProperty days;

    public PairProperties(String firstEmployee, String secondEmployee, String projectId, int days) {
        this.firstEmployee = new SimpleStringProperty(firstEmployee);
        this.secondEmployee = new SimpleStringProperty(secondEmployee);
        this.projectId = new SimpleStringProperty(projectId);
        this.days = new SimpleIntegerProperty(days);
    }

    public String getFirstEmployee() {
        return firstEmployee.get();
    }

    public String getSecondEmployee() {
        return secondEmployee.get();
    }

    public String getProjectId() {
        return projectId.get();
    }

    public int getDays() {
        return days.get();
    }
}
