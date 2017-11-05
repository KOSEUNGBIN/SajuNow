package com.landvibe.api;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.schedule.Schedule;
import com.landvibe.core.schedule.ScheduleBo;

@Configuration
@ComponentScan(basePackages = "learningtest.spring.scheduled")
@EnableScheduling

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private CompanyBo companyBo;

	@Autowired
	private ScheduleBo scheduleBo;

	@RequestMapping("/select")
	@ResponseBody
	public Object selectSchedule(@RequestParam("company_no") long company_no,
			@DateTimeFormat(pattern = "yyyy-MM-dd") String date) {

		System.out.println(company_no + "   " + date);
		return scheduleBo.getByNo(company_no, date);

	}

	@RequestMapping("/all")
	@ResponseBody
	public Object selectAll() {

		return scheduleBo.selectAll();

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public void insertFriend(@ModelAttribute Schedule schedule) {

		if (scheduleBo.isDuplicated(schedule.getCompany_no(), schedule.getDate()))
			scheduleBo.update(schedule);
		else
			scheduleBo.create(schedule);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH");

		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		String slot_no = "slot_" + timeFormat.format(new Date(System.currentTimeMillis()));

		companyBo.updateChatPossibilityAll(date, slot_no, CompanyController.getMAX_CHAT_COUNT());

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateFriend(@ModelAttribute Schedule schedule) {

		scheduleBo.update(schedule);

	}

	@Scheduled(cron = "0 0 * * * *")
	public void updateCompanyChatPossibility() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		String slot_no = "slot_" + timeFormat.format(new Date(System.currentTimeMillis()));

		companyBo.updateChatPossibilityAll(date, slot_no, CompanyController.getMAX_CHAT_COUNT());
		System.out.println(Thread.currentThread() + ": updateCompanyChatPossibility " + new Date());
	}

	@Scheduled(cron = "0 0 0 * * *")
	public void insertSchedule() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 14);
		String date = dateFormat.format(calendar.getTime());
		List<Company> companyList = companyBo.getAll();

		scheduleBo.insertScheduleAll(companyList, date);
		System.out.println(Thread.currentThread() + ": insertSchedule " + new Date());
	}

}
