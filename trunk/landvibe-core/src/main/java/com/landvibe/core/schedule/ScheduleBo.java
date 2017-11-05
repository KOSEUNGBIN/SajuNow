package com.landvibe.core.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landvibe.core.company.Company;

@Service
public class ScheduleBo {
	@Autowired
	private ScheduleDao scheduleDao;
	
	/**
	 * User �쎌�� 
	 * @param user
	 */
	public void create(Schedule schedule) {
		scheduleDao.insert(schedule);
	}
	
	public List<Schedule> selectAll() {
		return scheduleDao.selectAll();
	}

	/**
	 * User 遺��ъ�ㅺ린 
	 * @param user_no
	 * @return User 
	 */
	public Schedule getByNo(long company_no, String date) {
		return scheduleDao.selectByNo(company_no, date);
	}
	
	public boolean isDuplicated(long company_no, String date) {
		 List<Schedule> list = null;
		 boolean isDuplicated = false;
		 list = scheduleDao.selectByNoForDuplicate(company_no, date);
		 if(!list.isEmpty()) //由ъ�ㅽ�멸� 議댁�� �� 寃쎌��
			 isDuplicated = true;
		 System.out.println("test : " + list.toString() + "size : " + list.size() + "is empty ? : " + list.isEmpty());
		return isDuplicated;
	}
	

	/**
	 * User ���� 
	 * @param user_no
	 * @return ������ 媛��� 
	 */
	public int delete(long schedule_no){
		return scheduleDao.delete(schedule_no);
	}
	
	/**
	 * User 蹂�寃� 
	 * @param user
	 * @return 蹂�寃쎈�� 媛��� 
	 */
	//email, password, name, modify_date留� 蹂�寃� - 蹂�寃쎈�댁�� 異��� 異�
	public int update(Schedule schedule){
		return scheduleDao.update(schedule);
	}

	public void insertScheduleAll(List<Company> companyList, String date) {
		scheduleDao.insertScheduleAll(companyList, date);
	}

	public void insertScheduleNewCompany(long company_no, String date) {
		scheduleDao.insertScheduleNewCompany(company_no, date);
	}

}