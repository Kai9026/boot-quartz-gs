package com.github.kai9026.quartzgs.job;

import com.github.kai9026.quartzgs.repository.Campaign;
import com.github.kai9026.quartzgs.repository.CampaignRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class TestJob implements Job {

    private final static Logger logger = LoggerFactory.getLogger(TestJob.class);

    private CampaignRepository repository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String name = "test campaign";
        Campaign campaign = repository.findByName(name).orElseThrow(EntityNotFoundException::new);
        logger.info("Executing job ...");
        logger.info(" There is campaign: " + campaign.getName());
    }

    @Autowired
    public void setRepository(CampaignRepository repository) {
        this.repository = repository;
    }
}
