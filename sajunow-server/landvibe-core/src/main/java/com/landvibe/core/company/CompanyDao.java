package com.landvibe.core.company;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.user.User;

@Repository
public interface CompanyDao {
	
	int insert(Company company);
	
	Company select(@Param("company_no") long company_no );
	
	long selectCount();
	
	int selectByToken(@Param("company_token") String company_token);
	
	String selectForBlock(@Param("company_no") int company_no);
	
	List<Company> selectAll();
	
	List<Company> selectSection(@Param("selectNo") long selectNo);
	
	Company selectJoinCompanyToReport(@Param("company_no") long company_no);
	
	Company selectJoinCompanyToCategoryByCompany(@Param("company_no") long company_no);
	
	Company selectByEmail(@Param("email") String email);
	
	int totalOfCompanys();
	
	int selectCompanyReportCount(@Param("company_no") long company_no);
	
	int delete(@Param("company_no") long company_no );
	
	int update(Company company);
	
	Company Login(@Param("email") String email, @Param("password") String password);
	
	int updateToken(Company company);
	
	int updateRegId(Company company);
	
	int updatePremium(@Param("company_no") long company_no,@Param("premium") long premium);
	
	int updateMargin(@Param("company_no") long company_no,@Param("margin") long margin);
	
	List<Company> selectJoinCompanyToCategory(Map<String, Object> map);
	
	List<Company> selectJoinCategoryByCode(Map<String, Object> map);

	List<Company> selectAlljoinUserJoinCategory(Map<String, Object> map);
	
	List<Company> selectAllSimpleCategory();

	List<Company> selectAllOuterjoinUserJoinCategory(Map<String, Object> map);

	List<Company> selectOuterjoinUserJoinCategory(Map<String, Object> map);
	
	List<User> selectByUserRegId(@Param("user_reg_id") String user_reg_id);

	boolean updateChatCountAndChatPossibility(@Param("company_no") long company_no, @Param("variation") String variation,@Param("date") String date, @Param("slot_no") String slot_no);
	
	boolean updateSimpleChatCountAndChatPossibility(@Param("company_no") long company_no, @Param("variation") String variation,@Param("date") String date, @Param("slot_no") String slot_no);

	void updateChatPossibilityAll(@Param("date") String date, @Param("slot_no") String slot_no, @Param("MAX_CHAT_COUNT") int MAX_CHAT_COUNT);

	float getSum(long company_no);
	
	void updateScoreAverage(Company company);
	
	void updateChatPossibility(Company company);
	
	void updateSimpleChatPossibility(Company company);
	
	void updateHistoryPossibilityCount(Company company);
	
	void updateChatPossibilityResult(Company company);
	
	void updateSimpleChatPossibilityResult(Company company);
	
	void updateAllChatPossibilityResult(Company company);
	
	void updateChatSwitch(Company company);
}
