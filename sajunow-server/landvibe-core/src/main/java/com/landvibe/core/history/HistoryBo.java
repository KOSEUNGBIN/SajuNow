package com.landvibe.core.history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryBo {

	@Autowired
	private HistoryDao historyDao;

	/**
	 * 히스토리 생성 (채팅시작, end_date 제외)
	 * 
	 * @param history
	 */
	public long create(History history) {
		historyDao.insert(history);
		return historyDao.selectKey();

	}
	
	public long getLiveCount(){
		return historyDao.selectLiveCount();
	}
	
	public List<Object> getCompanyRank(){
		return historyDao.selectCompanyRank();
	}
	
	public long getCount(){
		return historyDao.selectCount();
	}

	/**
	 * 채팅 끝난 후 종료 시점 삽입
	 * 
	 * @param history_no
	 * @param end_date
	 */
	public void insertEnd(History history) {
		historyDao.insertEnd(history);
	}

	/**
	 * 하나의 히스토리,메세지 모두 가저오기
	 * 
	 * @param history_no
	 * @return
	 */
	public History getByNo(long history_no) {
		return historyDao.selectByNo(history_no);
	}

	/**
	 * 하나의 히스토리 가저오기
	 * 
	 * @param history_no
	 * @return
	 */
	public History getHistory(long history_no) {
		return historyDao.select(history_no);
	}

	/**
	 * user_no에 해당하는 상담중인 채팅개수를 조회하여 가져온다.
	 * 
	 * @param user_no
	 * @return 상담중인 개수
	 */
	public long getByUserConsertCount(long user_no) {
		return historyDao.selectHistoryUserConsertCount(user_no);
	}

	/**
	 * company_no에 해당하는 상담중인 채팅개수를 조회하여 가져온다.
	 * 
	 * @param company_no
	 * @return 상담중인 개수
	 */
	public long getByCompanyConsertCount(long company_no) {
		return historyDao.selectHistoryCompanyConsertCount(company_no);
	}

	/**
	 * 해당 user_no과 company_no의 상담중인 채팅방이 있는지 없는지 확인
	 * General
	 * @param List
	 * @return History
	 */
	public History getByhistoryEndYnGeneral(long user_no, long company_no) {
		return historyDao.selectByhistoryEndYnGeneral(user_no, company_no);
		
	}
	
	/**
	 * 해당 user_no과 company_no의 상담중인 채팅방이 있는지 없는지 확인
	 * Simple
	 * @param List
	 * @return History
	 */
	public History getByhistoryEndYnSimple(long user_no, long company_no) {
		return historyDao.selectByhistoryEndYnSimple(user_no, company_no);
	}


	/**
	 * history 상담 종료시 endYn update
	 * 
	 * @param history_nol
	 * @return
	 */

	public void updateEndYn(long history_no) {
		historyDao.updateEndYn(history_no);
	}
	
	/**
	 * simple history 하나의 메시지를 insert시 'simple_history_end_yn' update
	 * 
	 * @param history_nol
	 * @return
	 */

	public void updateSimpleEndyn(long history_no) {
		historyDao.updateSimpleEndyn(history_no);
	}

	/**
	 * 하나의 user_no에 대한 모든 history 가저오기
	 * 
	 * @param user_no
	 * @return List<History>
	 */
	public List<History> getByAllCompanyHistory(long user_no) {
		return historyDao.selectAllCompany(user_no);
	}

	/**
	 * 하나의 company_no에 대한 모든 history 가저오기
	 * 
	 * @param company_no
	 * @return List<History>
	 */
	public List<History> getByAllUserHistory(long company_no) {
		return historyDao.selectAllUser(company_no);
	}

	/**
	 * User일때 company의 reg_id를, Company일때 user의 reg_id를 리턴
	 * 
	 * @param isUser
	 * @param history_no
	 * @return
	 */
	public String getRegId(long sender, long history_no) {
		History history = historyDao.selectOnlyHistoryByNo(history_no);
		System.out.println("sender num :" + sender);
		if (sender < 10) {
			return history.getCompany_reg_id();
		} else {
			return history.getUser_reg_id();
		}
	}

	public void updateCompanyReg(History history) {

		historyDao.updateCompanyReg(history);
	}

	public void updateUserReg(History history) {

		historyDao.updateUserReg(history);
	}

	public void updateIsAlarmed(long history_no ,long is_report_alarmed) {

		historyDao.updateIsAlarmed(history_no , is_report_alarmed);
	}

	public void updateIsdelete_user(long history_no) {

		historyDao.updateIsdelete_user(history_no);
	}
	
	public void updateIsdelete_company(long history_no) {

		historyDao.updateIsdelete_company(history_no);
	}
	/**
	 * history의 역술인(countInitCompany) 또는 사용자(countInitUser)가 안읽은 메세지 개수 0으로 초기화
	 * 
	 * @param history_no
	 */
	public void countInitCompany(long history_no) {
		historyDao.countInitCompany(history_no);
	}

	public void countInitUser(long history_no) {
		historyDao.countInitUser(history_no);
	}

	public long selectUnAlarmedReportCount(long user_no) {
		return historyDao.selectUnAlarmedReportCount(user_no);
	}

	public void delete(long history_no) {

		historyDao.delete(history_no);
	}
	
}
