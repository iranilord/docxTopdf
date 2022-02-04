package ir.mrfn.rasha.rest;

import ir.mrfn.rasha.model.SchedulerModel;
import ir.mrfn.rasha.service.EmailService;
import ir.mrfn.rasha.service.SchedulerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class SchedulerController {
    private final SchedulerService schedulerService;


    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/add")
    public String  createJob(@RequestBody SchedulerModel schedulerModel){
        try {
            schedulerService.add(schedulerModel);
            return "Your Job successfully added !";
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "Error !";
    }

    @GetMapping("/susspand/{jobName}")
    public String susspandJob(@PathVariable String jobName){
        try {
            schedulerService.susspand(jobName);
            return "Your Job susspanded!";
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "Error !";
    }

    @GetMapping("/unsusspand/{jobName}")
    public String unsusspandJob(@PathVariable String jobName){
        try {
            schedulerService.unsusspand(jobName);
            return "Your Job Unsusspanded!";
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "Error !";
    }



}
