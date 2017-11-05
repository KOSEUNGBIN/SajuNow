package com.landvibe.core.friend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendBo {
	
	/**
	 * friendDao 객체 주입
	 */
	@Autowired
	private FriendDao friendDao;
	
	/**
	 * Friend 삽입 
	 * @param friend
	 */
	public void create(Friend friend) {
		friendDao.insert(friend);
	}
	
	/**
	 * Friend 삭제 
	 * @param friend_no
	 * @return 삭제된 개수 
	 */
	public int delete(long friend_no){
		return friendDao.delete(friend_no);
	}
	
	/**
	 * Friend 변경 
	 * @param friend
	 * @return 변경된 개수 
	 */
	public int update(Friend friend){
		return friendDao.update(friend);
	}
	

	/**
	 * Friend 불러오기 
	 * @param friend_no
	 * @return Friend 
	 */
	public Friend getByNo(long friend_no) {
		return friendDao.selectByNo(friend_no);
	}
	
	/**
	 * User가 추가한 모든 친구정보 불러오기 
	 * @param user_no
	 * @return
	 */
	public List<Friend> getByUser(long user_no){
		return friendDao.selectByUser(user_no);
	}
	
	
	/**
	 * 모든 Friend 불러오기 
	 * @return List<Friend>
	 */
	public List<Friend> getAll(){
		return friendDao.selectAll();
	}
	
	
	
}