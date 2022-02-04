package ir.mrfn.rasha.service;
import static org.quartz.JobKey.jobKey;
import ir.mrfn.rasha.job.SchedulerJob;
import ir.mrfn.rasha.model.SchedulerModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SchedulerService {
    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public JobDetail jobDetailBuilder(SchedulerModel schedulerModel){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobName",schedulerModel.getJobName());
        jobDataMap.put("group",schedulerModel.getGroup());
        jobDataMap.put("description",schedulerModel.getDescription());
        jobDataMap.put("cronExpression",schedulerModel.getCronExpression());

        JobDetail build = JobBuilder.newJob(SchedulerJob.class)
                .withIdentity(schedulerModel.getJobName(), schedulerModel.getGroup())
                .withDescription(schedulerModel.getDescription())
                .usingJobData(jobDataMap)
                .build();

        return build;
    }


    public Trigger triggerBuilder(JobDetail jobDetail){
        String corn = jobDetail.getJobDataMap().getString("cronExpression");

        CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(corn);

        CronTrigger build = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), jobDetail.getKey().getGroup())
                .withDescription(jobDetail.getDescription())
                .withSchedule(builder)
                .startNow().build();
        return build;
    }


    public void add(SchedulerModel schedulerModel) throws SchedulerException {
        JobDetail jobDetail = jobDetailBuilder(schedulerModel);
        Trigger trigger = triggerBuilder(jobDetail);
        scheduler.scheduleJob(jobDetail,trigger);
        log.info("new job added ...");

    }


    public void susspand(String jobName) throws SchedulerException {
        scheduler.pauseTrigger(new TriggerKey(jobName));
        log.info("job susspanded =>"+jobName);
    }

    public void unsusspand(String jobName) throws SchedulerException {
        scheduler.resumeTrigger(new TriggerKey(jobName));
        log.info("job Unsusspanded =>"+jobName);
    }
}
