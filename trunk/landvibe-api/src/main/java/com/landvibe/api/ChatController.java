package com.landvibe.api;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.landvibe.common.send.SendGcm;
import com.landvibe.core.chatmessage.ChatMessage;
import com.landvibe.core.chatmessage.ChatMessageBo;
import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.user.UserBo;

@RestController
@RequestMapping("/chat")
public class ChatController {

	private static final String LOGOUT = "LOGOUT";

	@Autowired
	ChatMessageBo chatMessageBo;

	@Autowired
	HistoryBo historyBo;

	@Autowired
	UserBo userBo;

	@Autowired
	CompanyBo companyBo;

	@Autowired
	SendGcm sendGcm;
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void sendMsg(@ModelAttribute ChatMessage chatMessage) {
		String sender_name = "";

		History history = historyBo.getHistory(chatMessage.getHistory_no());
		
		if (chatMessage.getSender() < 10) {
			sender_name += userBo.getByNo(history.getUser_no()).getName();
		} else {
			sender_name += companyBo.getByNo(history.getCompany_no()).getNick_name();
		}
		// GCM 발송
		sendGcm.send(chatMessage.getHistory_no(), chatMessage.getMessage(), chatMessage.getSender(), false, sender_name, history.getSelect_history() ,false);
	
		// 채팅내용 DB 저장
		chatMessageBo.create(chatMessage);
	}

	@ResponseBody
	@RequestMapping(value = "/terminate", method = RequestMethod.POST)
	public void terminateChat(@ModelAttribute ChatMessage chatMessage) {
		History history = historyBo.getHistory(chatMessage.getHistory_no());
		Company company = companyBo.getByNo(history.getCompany_no());
		String company_name = company.getNick_name();
		
		// GCM 발송
		sendGcm.send(chatMessage.getHistory_no(), "history terminate", chatMessage.getSender(), true, company_name ,history.getSelect_history(),false);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		String slot_no = "slot_" + timeFormat.format(new Date(System.currentTimeMillis()));

		historyBo.updateEndYn(chatMessage.getHistory_no());
		companyBo.updateChatCountAndChatPossibility(chatMessage.getCompany_no(), "DECREASE", date, slot_no);
	}
	
	@ResponseBody
	@RequestMapping(value = "/terminate/simple", method = RequestMethod.POST)
	public void terminateSimpleChat(@ModelAttribute ChatMessage chatMessage) {
		History history = historyBo.getHistory(chatMessage.getHistory_no());
		Company company = companyBo.getByNo(history.getCompany_no());
		String company_name = company.getNick_name();
		
		// 채팅내용 DB 저장
		chatMessageBo.create(chatMessage);
		
		// GCM 발송
		sendGcm.send(chatMessage.getHistory_no(), "simple history terminate", chatMessage.getSender(), true, company_name ,history.getSelect_history(),false);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		String slot_no = "slot_" + timeFormat.format(new Date(System.currentTimeMillis()));

		historyBo.updateEndYn(chatMessage.getHistory_no());
		companyBo.updateSimpleChatCountAndChatPossibility(company.getCompany_no(), "DECREASE", date, slot_no);
	}

}
