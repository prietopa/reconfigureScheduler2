package net.jm.pp.scheduler.quartz.jobs;

import java.util.Date;
import java.util.concurrent.Semaphore;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LicitacionJobQuartz extends QuartzJobBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(LicitacionJobQuartz.class);
	
	private Semaphore semaphoreJobLicitacionQuartz;
	
	public void setSemaphoreJobLicitacionQuartz(Semaphore semaphoreJobLicitacionQuartz) {
		this.semaphoreJobLicitacionQuartz = semaphoreJobLicitacionQuartz;
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		long dateTime = (new Date()).getTime();
		try {
			LOGGER.info(dateTime+"- LicitacionJobQuartz. INICIO.");
			LOGGER.trace(dateTime+"- LicitacionJobQuartz. Solicito permiso al semaforo.");
			
			if(semaphoreJobLicitacionQuartz.tryAcquire()) {
				semaphoreJobLicitacionQuartz.acquire();
				LOGGER.trace(dateTime+"- LicitacionJobQuartz. Permiso concedido.");
				
				// Do something
				Thread.sleep(5000L);
				// Do something
				
			} else {
				LOGGER.info(dateTime+"- LicitacionJobQuartz. No ejecutamos hay otro proceso en ejecucion.");
			}
			
		}catch(Exception e) {
			JobExecutionException jee = new JobExecutionException();
			jee.setRefireImmediately(false);
			jee.setStackTrace(e.getStackTrace());
			jee.setUnscheduleAllTriggers(false);
			jee.setUnscheduleFiringTrigger(false);
			throw jee;
		}finally {
			LOGGER.info(dateTime+"- LicitacionJobQuartz. FIN.");
			semaphoreJobLicitacionQuartz.release();
		}
	}

}
