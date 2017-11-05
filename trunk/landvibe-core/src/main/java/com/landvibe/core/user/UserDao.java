package com.landvibe.core.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.company.Company;

@Repository
public interface UserDao {
	
	int insert(User user);
	
	long selectKey();

	User selectByNo(@Param("user_no") long user_no);
	
	long selectCount();
	
	User selectForLogin(@Param("email") String email, @Param("password") String password);
	
	int selectByToken(@Param("user_token") String user_token);
	
	String selectForBlock(@Param("user_no") int user_no);
	
	User selectByEmail(@Param("email") String email);
	
	List<User> selectAll();
	
	List<User> selectSection(@Param("selectNo") long selectNo);
	
	List<User> selectJoinFavorite();

	int delete(@Param("user_no") long user_no);
	
	int update(User user); //email, password, name, modify_date만 변경
	
	int updateToken(User user);
	
	int updateRegId(User user);
	
	List<User> selectByCompanyRegId(@Param("company_reg_id") String company_reg_id);
	
	User selectUserRegIdByNaverId(@Param("naver_id") String naver_id);
	
	User selectUserRegIdByFacebookId(@Param("facebook_id") String facebook_id);
	
	void updatePassword(User user);
	
	void updateNaverId(User user);
	
	void updateFacebookId(User user);
	
	
	
}
