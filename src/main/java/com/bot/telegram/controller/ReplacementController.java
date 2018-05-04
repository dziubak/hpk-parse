package com.bot.telegram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bot.telegram.component.interfaces.IReplacement;
import com.bot.telegram.service.ParseReplacement;

@RestController
public class ReplacementController implements Runnable {
	@Autowired
	private IReplacement iReplacement;
	
	public String createChild() {
		ParseReplacement parseReplacement = new ParseReplacement();
		parseReplacement.parseHtml();
		return "Child was created";
	}

	@Override
	public void run() {
		createChild();
		
	}

}
