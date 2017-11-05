package com.landvibe.core.chatmessage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.friend.Friend;

@Repository
public interface ChatMessageDao {
	
	int insert(ChatMessage chatMessage);

}

