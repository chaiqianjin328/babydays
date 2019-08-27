package com.babydays.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/app")
public class CalendarController {

	
	@RequestMapping("/calendar")
	public String calendar() {
		return "app/calendar";
	}
	
	
	
	
	
}
