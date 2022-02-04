package ir.mrfn.rasha.job;

import ir.mrfn.rasha.service.EmailService;
import ir.mrfn.rasha.util.Converter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@Slf4j
public class SchedulerJob implements Job {
    private EmailService emailService;

    @Autowired
    public SchedulerJob(EmailService emailService){
        this.emailService = emailService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Converter converter = new Converter();
        try {
            converter.convertToPdf();
        } catch (IOException e) {
            log.error("Error doing Job ....");
            emailService.send("SpringBoot@mrfn.ir","Email@test.ir","Error Run JOB",
                    e.getMessage());

        }
    }
}
