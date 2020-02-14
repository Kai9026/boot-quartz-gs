package com.github.kai9026.quartzgs.controller;

import com.github.kai9026.quartzgs.job.TestJob;
import com.github.kai9026.quartzgs.payload.request.JobRequest;
import com.github.kai9026.quartzgs.payload.response.JobResponse;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
public class JobController {

    private final static Logger logger = LoggerFactory.getLogger(JobController.class);

    private final Scheduler scheduler;

    public JobController(final Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostMapping("/job")
    public ResponseEntity<JobResponse> jobMethod(@RequestBody @Valid JobRequest request) {
        ZonedDateTime dateTime = ZonedDateTime.of(request.getTimeTrigger(), request.getTimeZone());
        JobDetail jobDetail = buildJobDetail(request);
        Trigger trigger = buildJobTrigger(jobDetail, dateTime);
        try {
            logger.info("Launching job ...");
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
           // TODO
        }

        JobResponse result = JobResponse.builder()
                .success(true)
                .jobId(jobDetail.getKey().getName())
                .jobGroup(jobDetail.getKey().getGroup())
                .message("Job scheduled")
                .build();
        return ResponseEntity.ok(result);
    }

    private JobDetail buildJobDetail(JobRequest request) {
        JobDataMap data = new JobDataMap();
        data.put("dateJob", request.getTimeTrigger());
        return JobBuilder.newJob(TestJob.class)
                .withIdentity(UUID.randomUUID().toString(), "test-job")
                .withDescription("Test job")
                .usingJobData(data)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime dateTime) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "test-job")
                .withDescription("Test job")
                .startAt(Date.from(dateTime.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(1))
                .build();
    }

}
