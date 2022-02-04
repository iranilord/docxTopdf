package ir.mrfn.rasha.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SchedulerModel {

    private String jobName;
    private String group="generation";
    private String description;
    private String cronExpression;

    public SchedulerModel(String jobName, String group, String description, String cronExpression) {
        this.jobName = jobName;
        this.group = group;
        this.description = description;
        this.cronExpression = cronExpression;
    }
}
