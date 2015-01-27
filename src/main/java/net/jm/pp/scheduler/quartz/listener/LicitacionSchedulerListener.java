package net.jm.pp.scheduler.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("licitacionSchedulerListener")
public class LicitacionSchedulerListener implements SchedulerListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(LicitacionSchedulerListener.class);
	public String name = "licitacionSchedulerListener";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setGlobalListenerName(String name) {
        this.name = name;
    }

	public void jobScheduled(Trigger trigger) {
		String jobName = trigger.getJobKey().getName();
		LOGGER.info("jobScheduled: " + jobName);
	}

	public void jobAdded(JobDetail jobDetail) {
		LOGGER.info("jobAdded: " + jobDetail.getKey().getName());
	}

	public void schedulerError(String msg, SchedulerException cause) {
		LOGGER.info("schedulerError. msg: " + msg + ", cause: " + cause.getMessage());
	}

	public void schedulerInStandbyMode() {
		LOGGER.info("schedulerInStandbyMode.");
	}

	public void schedulerStarted() {
		LOGGER.info("schedulerStarted.");
	}

	public void schedulerShutdown() {
		LOGGER.info("schedulerShutdown.");
	}

	public void schedulerShuttingdown() {
		LOGGER.info("schedulerShuttingdown...");
	}

	public void jobUnscheduled(TriggerKey triggerKey) {
		LOGGER.info("jobUnscheduled - triggerName: " + triggerKey.getName());
	}

	public void triggerPaused(TriggerKey triggerKey) {
		LOGGER.info("triggerPaused: " + triggerKey.getName());
	}

	public void triggersPaused(String triggerGroup) {
		LOGGER.info("triggerPaused triggerGroup: " + triggerGroup);
	}

	public void triggerResumed(TriggerKey triggerKey) {
		LOGGER.info("triggerResumed: " + triggerKey.getName());
	}

	public void triggersResumed(String triggerGroup) {
		LOGGER.info("triggersResumed triggerGroup: " + triggerGroup);
	}

	public void jobDeleted(JobKey jobKey) {
		LOGGER.info("jobDeleted: " + jobKey.getName());
	}

	public void jobPaused(JobKey jobKey) {
		LOGGER.info("jobPaused: " + jobKey.getName());
	}

	public void jobsPaused(String jobGroup) {
		LOGGER.info("jobsPaused - jobGroup: " + jobGroup);
	}

	public void jobResumed(JobKey jobKey) {
		LOGGER.info("jobPaused: " + jobKey.getName());
	}

	public void jobsResumed(String jobGroup) {
		LOGGER.info("jobsResumed - jobGroup: " + jobGroup);
	}

	public void schedulerStarting() {
		LOGGER.info("schedulerStarting.");
	}

	public void schedulingDataCleared() {
		LOGGER.info("schedulingDataCleared.");
	}

	public void triggerFinalized(Trigger trigger) {
		LOGGER.info("triggerFinalized: " + trigger.getKey().getName());
	}

}
