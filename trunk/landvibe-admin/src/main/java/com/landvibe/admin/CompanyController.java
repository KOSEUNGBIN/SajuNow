package com.landvibe.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.companytoreport.CompanyToReportBo;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;

@Controller
@RequestMapping("company")
public class CompanyController {

	@Autowired
	private CompanyBo companyBo;
	
	@Autowired
	private CompanyToReportBo companyToReportBo;

	@Autowired
	private HistoryBo historyBo;

	@RequestMapping("/{page:[0-9]+}")
	public ModelAndView companyMain(@PathVariable long page) {

		ModelAndView mav = new ModelAndView("company/list");

		long selectNo = (page - 1) * 15;

		long companyListLength = companyBo.getCount();
		long companyTotalPage = (companyListLength - 1) / 15 + 1;

		List<Long> pagegroup_no = new ArrayList<Long>();

		pagegroup_no.add((page - 1) - (page - 1) % 5 + 1);

		if ((page - 1) / 5 != (companyTotalPage - 1) / 5) {
			pagegroup_no.add((page - 1) - (page - 1) % 5 + 5);
		} else {
			pagegroup_no.add(companyTotalPage);
		}

		if (page > 5) {
			pagegroup_no.add((page - 1) - (page - 1) % 5 - 4);
		} else {
			pagegroup_no.add(1L);
		}

		if (page < (companyTotalPage - 1) - (companyTotalPage - 1) % 5 + 1) {
			pagegroup_no.add((page - 1) - (page - 1) % 5 + 6);
		} else {
			pagegroup_no.add(companyTotalPage);
		}

		System.out.println(selectNo);

		mav.addObject("pagegroup_no", pagegroup_no);
		mav.addObject("companyList", companyBo.getOfSection(selectNo));

		return mav;
	}

	@RequestMapping("/detail/{companyNo:[0-9]+}")
	public ModelAndView companyDetail(@PathVariable long companyNo) {

		ModelAndView mav = new ModelAndView("company/detail");
		Company company = companyBo.getByNo(companyNo);
		mav.addObject("company", company);

		List<History> historyAllUserList = historyBo.getByAllUserHistory(companyNo);
		mav.addObject("UserList", historyAllUserList);
		return mav;
	}

	@RequestMapping(value = "/pause", method = RequestMethod.POST)
	@ResponseBody
	public boolean companyPause(@RequestParam("pause") boolean pause, @RequestParam("companyNo") long companyNo) {

		Company company = companyBo.getByNo(companyNo);
		company.setCompany_no(companyNo);
		if (pause)
			company.setCompany_reg_id("BLOCK");
		else
			company.setCompany_reg_id("LOGOUT");

		companyBo.updateRegId(company);

		company.setChat_possibility(false);
		companyBo.updateChatPossibility(company);
		List<History> companyHistoryList = historyBo.getByAllUserHistory(companyNo);

		for (int i = 0; i < companyHistoryList.size(); i++) {
			companyHistoryList.get(i).setCompany_reg(company.getCompany_reg_id());
			historyBo.updateCompanyReg(companyHistoryList.get(i));
		}

		return true;
	}

	@RequestMapping(value = "/report/{companyNo:[0-9]+}")
	public ModelAndView companyPause(@PathVariable long companyNo) {

		ModelAndView mav = new ModelAndView("company/report");

		Company company = companyBo.getJoinCompanyToReport(companyNo);
		company.setReport_count(companyBo.getCompanyReportCount(companyNo));

		double score_average = company.getScore_average();
		double score_average_temp = Math.floor(score_average);

		if (score_average_temp <= score_average && score_average < score_average_temp + 0.5)
			company.setScore_average(score_average_temp);
		else if (score_average_temp + 0.5 <= score_average && score_average < score_average_temp + 1)
			company.setScore_average(score_average_temp + 0.5);
		else
			;
		mav.addObject("company", company);

		return mav;
	}

	@RequestMapping(value = "/possibility/{companyNo:[0-9]+}", method = RequestMethod.POST)
	public String companyUpdateHistoryPossibliityCount(Company companyTemp, @PathVariable long companyNo) {

		Company company = companyBo.getByNo(companyNo);
		long currentHistoryCount = company.getChat_count();
		long history_possibility_count = companyTemp.getHistory_possibility_count();

		company.setHistory_possibility_count(history_possibility_count);
		company.setChat_possibility(history_possibility_count > currentHistoryCount ? true : false);

		if (company.isHistory_simple()) {
			long currentSimpleHistoryCount = company.getSimple_chat_count();
			long simple_history_possibility_count = companyTemp.getSimple_history_possibility_count();
			
			company.setSimple_history_possibility_count(simple_history_possibility_count);
			company.setSimple_chat_possibility(simple_history_possibility_count > currentSimpleHistoryCount ? true : false);
		}

		companyBo.updateHistoryPossibilityCount(company);

		return "redirect:/company/detail/" + companyNo;
	}
	
	@RequestMapping(value = "/update/premium/{company_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public String updatePremium(@PathVariable long company_no , @RequestParam("premium") int premium) {
		companyBo.updatePremium(company_no,premium);
		
		return "SUCCESS";
	}
	
	@RequestMapping(value = "/report/delete/{company_no:[0-9]+}", method = RequestMethod.POST)
	public String companyDeleteReport(@PathVariable long company_no, @RequestParam("report_no_list") List<Long> report_no_list) {
		
		companyToReportBo.deleteList(report_no_list);
		
		return "redirect:/company/report/" + company_no;
	}
	
	@RequestMapping(value = "/update/margin/{company_no:[0-9]+}", method = RequestMethod.POST)
	public String companyUpdateMargin(@PathVariable long company_no, @RequestParam("margin") int margin) {
		
		companyBo.updateMargin(company_no,margin);
		
		return "redirect:/company/detail/" + company_no;
	}



}
