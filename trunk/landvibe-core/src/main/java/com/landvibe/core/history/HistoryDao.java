
package com.landvibe.core.history;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.chatmessage.ChatMessage;


@Repository
public interface HistoryDao {
	
	void insert(History histroy);
	
	//history_no 저장
	long selectKey();
	
	long selectLiveCount();
	
	long selectCount();
	
	List<Object> selectCompanyRank();
	
	void insertEnd(History history);
	
	void updateUserReg(History history);
	
	void updateCompanyReg(History history);
	
	void updateEndYn(@Param("history_no") long history_no);
	
	void updateSimpleEndyn(@Param("history_no") long history_no);
	
	void updateIsdelete_user(@Param("history_no") long history_no);
	
	void 	updateIsdelete_company(@Param("history_no") long history_no);

	History selectByNo(@Param("history_no") long history_no);
	
	List<History> selectAllCompany(@Param("user_no") long user_no);
	
	List<History> selectAllUser(@Param("company_no") long company_no);
	
	History selectOnlyHistoryByNo(@Param("history_no") long history_no);
	
	History selectByhistoryEndYnGeneral(@Param("user_no") long user_no,@Param("company_no") long company_no);
	
	History selectByhistoryEndYnSimple(@Param("user_no") long user_no,@Param("company_no") long company_no); 
	
	long selectHistoryUserConsertCount(@Param("user_no") long user_no); 
	
	long selectHistoryCompanyConsertCount(@Param("company_no") long company_no); 
		
	void userCountUp(@Param("history_no") long history_no);
	
	void companyCountUp(@Param("history_no") long history_no);
	
	void countInitCompany(@Param("history_no") long history_no);
	
	void countInitUser(@Param("history_no") long history_no);
	
	void updateIsAlarmed(@Param("history_no") long history_no , @Param("is_report_alarmed") long is_report_alarmed);
	
	long selectUnAlarmedReportCount(@Param("user_no") long user_no);
	
	History select(@Param("history_no") long history_no);
	
	void delete(@Param("history_no") long history_no);
	
	
	
}
