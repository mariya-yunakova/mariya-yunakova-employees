package pair.task.module;

import java.util.Date;

/** Representation of csv line content. */
public class CsvData {
    private String employee;
    private String project;
    private final Date startDate;
    private final Date endDate;

    public CsvData(String employee, String project, Date startDate, Date endDate) {
        this.employee = employee;
        this.project = project;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
