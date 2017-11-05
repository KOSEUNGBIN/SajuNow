package com.landvibe.core.friend;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendDao {
	
	int insert(Friend friend);
	
	int delete(@Param("friend_no") long friend_no);
	
	int update(Friend friend); 

	Friend selectByNo(@Param("friend_no") long friend_no);
	
	List<Friend> selectByUser(@Param("user_no") long user_no);
	
	List<Friend> selectAll();
	
	
}
