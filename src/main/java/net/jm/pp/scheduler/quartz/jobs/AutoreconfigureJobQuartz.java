package net.jm.pp.scheduler.quartz.jobs;

import net.jm.pp.scheduler.quartz.util.ReconfigureCron;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class AutoreconfigureJobQuartz extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoreconfigureJobQuartz.class);
	
	private ReconfigureCron reconfigureCron;
	
	public void setReconfigureCron(ReconfigureCron reconfigureCron) {
		this.reconfigureCron = reconfigureCron;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			LOGGER.info("Hilo: "+Thread.currentThread().getId()+". AutoreconfigureJobQuartz has sido levantado por el Scheduler. Invocamos a ReconfigureCron.");
			reconfigureCron.reconfigure();
			LOGGER.info("Hilo: "+Thread.currentThread().getId()+". Fin.");
		}catch(Exception e) {
			JobExecutionException jee = new JobExecutionException();
			jee.setRefireImmediately(false);
			jee.setStackTrace(e.getStackTrace());
			jee.setUnscheduleAllTriggers(false);
			jee.setUnscheduleFiringTrigger(false);
			throw jee;
		}
	}

}
