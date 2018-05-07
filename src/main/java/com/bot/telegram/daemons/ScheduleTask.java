package com.bot.telegram.daemons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bot.telegram.service.ParseReplacement;

/**
 * Class for schedule task
 * 
 * @author yagi
 *
 */
@Service
@EnableScheduling
public class ScheduleTask {

	private final static Logger LOGGER = Logger.getLogger(ScheduleTask.class);

	@Autowired
	private ParseReplacement parseReplacement;

	/**
	 * Run parse replacements every 1 hour (60 min)
	 */
	@Scheduled(fixedRate = 60 * 60 * 1000)
	public void scheduleTaskRunParseReplacement() {
		LOGGER.info("SCHEDULED | Start parse replacements.");

		parseReplacement.parseReplacements();
	}

}
