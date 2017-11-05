package com.landvibe.core.schedule;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.landvibe.core.company.Company;

@Repository
public interface ScheduleDao {
	
	int insert(Schedule schedule);

	Schedule selectByNo(@Param("company_no") long company_no,@Param("date") String date );
	
	int delete(@Param("schedule_no") long schedule_no);
	
	int update(Schedule schedule); 
	
	List<Schedule> selectAll();
	
	List<Schedule> selectByNoForDuplicate(@Param("company_no") long company_no,@Param("date") String date);
		
	void insertScheduleAll(@Param("companyList") List<Company> companyList, @Param("date") String date);

	void insertScheduleNewCompany(@Param("company_no") long company_no, @Param("date") String date);

}
