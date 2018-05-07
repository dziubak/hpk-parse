package com.bot.telegram.daemons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.bot.telegram.service.ParseReplacement;

/**
 * Class for schedule task
 * 
 * @author yagi
 *
 */
@EnableScheduling
public class ScheduleTask {

	@Autowired
	private ParseReplacement parseReplacement;

	/**
	 * Run parse replacements every 60 seconds (1 min)
	 */
	@Scheduled(fixedRate = 60 * 1000)
	public void scheduleTaskRunParseReplacement() {
		parseReplacement.parseReplacements();
	}

}
