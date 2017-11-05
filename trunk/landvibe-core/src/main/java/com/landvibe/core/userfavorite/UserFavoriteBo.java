package com.landvibe.core.userfavorite;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landvibe.core.user.UserDao;

@Service
public class UserFavoriteBo {

	@Autowired
	private UserFavoriteDao userFavoriteDao;
	
	public void insert(long user_no, long company_no) {
		userFavoriteDao.insert(user_no, company_no);
	}
	
	public void delete(long user_no, long company_no) {
		userFavoriteDao.delete(user_no, company_no);
	}
	
	public List<UserFavorite> select(long user_no, long company_no) {
		return userFavoriteDao.select(user_no, company_no);
	}
	public long selectFavoriteCount(long company_no){
		return userFavoriteDao.selectFavoriteCount(company_no);
	}
}
