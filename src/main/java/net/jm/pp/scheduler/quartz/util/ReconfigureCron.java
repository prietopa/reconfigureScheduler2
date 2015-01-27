package net.jm.pp.scheduler.quartz.util;

import java.util.Date;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.quartz.CronScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ReconfigureCron {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReconfigureCron.class);
	
	private static final String LICITACION_CRON_EXPRESSION = "scheduler.trigger.licitacion.cron.expression";
	private static final String LICITACION_CRON_NAME = "cronLicitacionTrigger";
	private static final String LICITACION_CRON_GROUP = "DEFAULT";

	@Autowired 
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private PropertiesConfiguration applicationProps;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reconfigure() {
		LOGGER.info("ReconfigureCron.reconfigure: " + new Date());
		
		if (applicationProps.getReloadingStrategy().reloadingRequired()) {
			LOGGER.info("¡Se ha modificiado el fichero de cron!.");
			String newCronExpression = (String) applicationProps.getProperty(LICITACION_CRON_EXPRESSION);
			LOGGER.info("La nueva espresion cron es: " + newCronExpression);
			
			Scheduler quartz = schedulerFactoryBean.getScheduler();
			try {
				// retrieve the trigger
				TriggerKey tk = new TriggerKey(LICITACION_CRON_NAME, LICITACION_CRON_GROUP);
				Trigger oldTrigger = quartz.getTrigger(tk);

				// obtain a builder that would produce the trigger
				TriggerBuilder tb = oldTrigger.getTriggerBuilder();
				Trigger newTrigger = (Trigger) tb.withSchedule(CronScheduleBuilder.cronSchedule(newCronExpression)).build();
				
				quartz.rescheduleJob(tk, newTrigger);
				
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error("ERROR al reconfigurar el cron: ", e);
			}
		} else {
			LOGGER.info("NO se ha modificiado el fichero de cron.");
		}
	}
}
