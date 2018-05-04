package com.bot.telegram.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.bot.telegram.service.ParseReplacement;

@SpringBootApplication
@ComponentScan("com.bot.telegram")
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
		
		ParseReplacement parseReplacement = new ParseReplacement();
		parseReplacement.parseHtml();
	}
}
