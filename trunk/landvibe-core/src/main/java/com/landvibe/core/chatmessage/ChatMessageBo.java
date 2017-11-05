
	package com.landvibe.core.chatmessage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landvibe.core.friend.Friend;
import com.landvibe.core.history.HistoryDao;


@Service
public class ChatMessageBo {
	
	@Autowired
	ChatMessageDao chatMessageDao;
	
	@Autowired
	HistoryDao historyDao;
	
	
	/**
	 * 메세지 입력 
	 * @param chatMessage
	 * @return
	 */
	public int create(ChatMessage chatMessage){
		
		//보낸사람이 user -> company +1
		//보낸사람이 company -> user +1
		if(chatMessage.getSender() < 10){
			historyDao.companyCountUp(chatMessage.getHistory_no());
		}else{
			historyDao.userCountUp(chatMessage.getHistory_no());
		}
		
		
		return chatMessageDao.insert(chatMessage);//메세지 저장 
	}
	

}

