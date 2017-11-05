package com.landvibe.core.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landvibe.core.company.Company;

@Service
public class UserBo {
	@Autowired
	private UserDao userDao;
	
	/**
	 * User 삽입 
	 * @param user
	 */
	public long create(User user) {
		 userDao.insert(user);
		return userDao.selectKey();
	}

	/**
	 * User 불러오기 
	 * @param user_no
	 * @return User 
	 */
	public User getByNo(long user_no) {
		return userDao.selectByNo(user_no);
	}
	
	public long getCount() {
		return userDao.selectCount();
	}
	
	public User selectForLogin(String email, String password){
		return userDao.selectForLogin(email, password);
	}
	
	public int selectByToken(String user_token){
		return userDao.selectByToken(user_token);
	}
	
	public String selectForBlock(int user_no){
		return userDao.selectForBlock(user_no);
	}
	
	
	/**
	 * Email 중복 확인 -> 중복된 email의 User 불러오기 
	 * @param user_no
	 * @return User 
	 */
	public User selectByEmail(String email){
		return userDao.selectByEmail(email);
	}
	
	
	/**
	 * 모든 User 불러오기 
	 * @return List<User>
	 */
	public List<User> getAll(){
		return userDao.selectAll();
	}
	
	public List<User> getOfSection(long selectNo){
		return userDao.selectSection(selectNo);
	}
	
	public List<User> getUserFavorite(){
		return userDao.selectJoinFavorite();
	}
	
	/**
	 * User 삭제 
	 * @param user_no
	 * @return 삭제된 개수 
	 */
	public int delete(long user_no){
		return userDao.delete(user_no);
	}
	
	/**
	 * User 변경 
	 * @param user
	 * @return 변경된 개수 
	 */
	//email, password, name, modify_date만 변경 - 변경내용 추후 추
	public int update(User user){
		return userDao.update(user);
	}
	
	public int updateToken(User user){
		return userDao.updateToken(user);
	}
	
	public int updateRegId(User user){
		return userDao.updateRegId(user);
	}
	
	public boolean selectByCompanyRegId(String company_reg_id){
		
		int isExist = userDao.selectByCompanyRegId(company_reg_id).size();
		
		if(isExist > 0)
			return false;
		else
			return true;
	}
	
	public User selectUserRegIdByNaverId(String naver_id){
		return userDao.selectUserRegIdByNaverId(naver_id);
	}
	
	public User selectUserRegIdByFacebookId(String facebook_id){
		return userDao.selectUserRegIdByFacebookId(facebook_id);
	}
	
	public void updatePassword(User user){
		userDao.updatePassword(user);
	}
	
	public void updateNaverId(User user){
		userDao.updateNaverId(user);
	}
	
	public void updateFacebookId(User user){
		userDao.updateFacebookId(user);
	}
}