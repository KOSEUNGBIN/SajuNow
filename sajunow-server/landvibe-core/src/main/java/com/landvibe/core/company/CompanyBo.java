package com.landvibe.core.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landvibe.core.user.User;

@Service
public class CompanyBo {

	@Autowired
	private CompanyDao companyDao;

	/**
	 * ������������������ ������������
	 * 
	 * @param company
	 * @return
	 */
	public int create(Company company) {
		return companyDao.insert(company);
	}
	
	public long getCount(){
		return companyDao.selectCount();
	}

	public Company Login(String email, String password)
	{
		return companyDao.Login(email, password);
	}	
	
	/**
	 * ������������������ ������嶺�占썲��占� 占쎈������쇽옙���э옙釉���
	 * 
	 * @param company_no
	 * @return Company
	 */
	public Company getByNo(long company_no) {
		return companyDao.select(company_no);
	}
	
	public float getSum(long company_no) {
		return companyDao.getSum(company_no);
	}
	
	/**
	 * Email 중복 확인 -> 중복된 email의 User 불러오기 
	 * @param user_no
	 * @return User 
	 */
	public Company selectByEmail(String email){
		return companyDao.selectByEmail(email);
	}
	
	public int selectByToken(String company_token){
		return companyDao.selectByToken(company_token);
	}
	
	public String selectForBlock(int company_no){
		return companyDao.selectForBlock(company_no);
	}
	
	public void updateScoreAverage(Company company){
		companyDao.updateScoreAverage(company);
	}


	/**
	 * 嶺�占쏙옙�우����占� ������������������ 占쎈������쇽옙���э옙釉��� (deprecated ������������ )
	 * 
	 * @return List<Company>
	 */
	public List<Company> getAll() {
		return companyDao.selectAll();
	}
	
	public List<Company> getOfSection(long selectNo){
		return companyDao.selectSection(selectNo);
	}

	/**
	 * ProfileActivity ������������������ �������곤옙��占�, �������ル���� 占쎈������쇽옙���э옙釉��� (Join Report)
	 * 
	 * @return List<Company>
	 */
	public Company getJoinCompanyToReport(long company_no) {
		Company company = companyDao.selectJoinCompanyToReport(company_no);
		if (company == null)
			return companyDao.select(company_no);
		return company;
	}
	
	/**
	 * ProfileActivity ������������������ �������곤옙��占�, �������ル���� 占쎈������쇽옙���э옙釉��� (Join Report)
	 * 
	 * @return List<Company>
	 */
	public Company getJoinCompanyToCategoryByCompany(long company_no) {
		Company company = companyDao.selectJoinCompanyToCategoryByCompany(company_no);
		if (company == null)
			return companyDao.select(company_no);
		return company;
	}

	/**
	 * 占싸우�� ������������������ ������
	 * 
	 * @return 占싸우�� ������������������ ������
	 */
	public int getTotalOfCompanys() {
		return companyDao.totalOfCompanys();
	}
	
	public int getCompanyReportCount(long company_no) {
		return companyDao.selectCompanyReportCount(company_no);
	}

	/**
	 * ������������������ ������������
	 * 
	 * @param company_no
	 * @return ������������������ ����������������占썲������ ������
	 */
	public int delete(long company_no) {
		return companyDao.delete(company_no);
	}

	/**
	 * ������������������ �������곤옙��占� �곤옙���뀐옙占썲��占�
	 * 
	 * @param company
	 * @return �곤옙���뀐옙占쏙옙占썲������ ����������������占썲������ ������
	 */
	public int update(Company company) {
		return companyDao.update(company);
	}
	
	public int updateToken(Company company){
		return companyDao.updateToken(company);
	}
	
	public int updateRegId(Company company){
		return companyDao.updateRegId(company);
	}
	
	public int updatePremium(long company_no, long premium){
		return companyDao.updatePremium(company_no, premium);
	}
	
	public int updateMargin(long company_no, long margin){
		return companyDao.updateMargin(company_no, margin);
	}
	
	
	/**
	 * ������������������ + ���놂옙占썲������占썩�⑥��占쎄��� 嶺�占쏙옙�우����占� 占쎄�占썲���э옙������占� 占쎈������쇽옙���э옙釉��� (����������占쏙옙��占�)
	 * @param start
	 * @return
	 */
	public List<Company> getJoinCompany(long start){
		
		final long SIZEOFPAGE = 30; // ��������怨ㅼ�������� ��占썲������������占쎌�쇱����占� ����������占쏙옙��������占� �띰옙��������占� 
		
		int total = companyDao.totalOfCompanys();
		if(start > total){
			start = total;	//������������������������ 占싸우���띰옙���������쇽옙占쏙옙������ ����占쏙옙������ ����������占썲���뱄옙源��� (����������占쏙옙������ ������) 
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("START", start);
		map.put("SIZE", SIZEOFPAGE);
		
		List<Company> list = companyDao.selectJoinCompanyToCategory(map);
		
		for(int i=0; i < list.size(); i++)
		{
			double score = list.get(i).getScore_average();
			score = Math.round(score*10)/10.0;
			list.get(i).setScore_average(score);
			list.get(i).setReport_count(getCompanyReportCount(list.get(i).getCompany_no()));
		}
		
		return list;
	}
	
	
	/**
	 * ���놂옙占썲������占썩�⑥��占쎄�占썲��占� 嶺�占쏙옙�우����占� ������������������ + ���놂옙占썲������占썩�⑥��占쎄��� 占쎄�占썲���э옙������占� 占쎈������쇽옙���э옙釉��� (����������占쏙옙��占�) 
	 * @param start
	 * @param category_code
	 * @return
	 */
	public List<Company> getJoinCompanyByCategory(long start, long category_code){
		
		final long SIZEOFPAGE = 30; // ��������怨ㅼ�������� ��占썲������������占쎌�쇱����占� ����������占쏙옙��������占� �띰옙��������占� 

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("START", start);
		map.put("SIZE", SIZEOFPAGE);
		map.put("category_code", category_code);
		
		List<Company> list = companyDao.selectJoinCategoryByCode(map);
		
		for(int i=0; i < list.size(); i++)
		{
			double score = list.get(i).getScore_average();
			score = Math.round(score*10)/10.0;
			list.get(i).setScore_average(score);
			list.get(i).setReport_count(getCompanyReportCount(list.get(i).getCompany_no()));
		}
		return list;
	}

	public List<Company> getAllSimpleCategory() {
		return companyDao.selectAllSimpleCategory();
	}

	
	public List<Company> getAllCompanyjoinUserjoinCategory(long user_no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_no", user_no);
		return companyDao.selectAlljoinUserJoinCategory(map);
	}
	
	public List<Company> getAllCompanyOuterjoinUserjoinCategory(long user_no, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_no", user_no);
		map.put("limit", limit);
		map.put("offset", offset);
		return companyDao.selectAllOuterjoinUserJoinCategory(map);
	}
	
	public List<Company> getCompanyOuterjoinUserjoinCategory(long user_no, int category_code, int offset, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_no", user_no);
		map.put("category_code", category_code);
		map.put("limit", limit);
		map.put("offset", offset);
		return companyDao.selectOuterjoinUserJoinCategory(map);
	}	
	
	public boolean selectByUserRegId(String user_reg_id){
		
		int isExist = companyDao.selectByUserRegId(user_reg_id).size();
		
		System.out.println("isExist : " + isExist);
		
		if(isExist > 0)
			return false;
		else
			return true;
	}
	
	public void updateChatCountAndChatPossibility(long company_no, String variation, String date, String slot_no) {
		companyDao.updateChatCountAndChatPossibility(company_no, variation, date, slot_no);
		Company temp = companyDao.select(company_no);
		if (temp.getChat_possibility() && temp.isChat_switch()) {
			temp.setChat_possibility_result(true);
		} else {
			temp.setChat_possibility_result(false);
		}
		companyDao.updateChatPossibilityResult(temp);
	}
	
	public void updateSimpleChatCountAndChatPossibility(long company_no, String variation, String date, String slot_no) {
		companyDao.updateSimpleChatCountAndChatPossibility(company_no, variation, date, slot_no);
		Company temp = companyDao.select(company_no);
		if (temp.isSimple_chat_possibility() && temp.isChat_switch()) {
			temp.setSimple_chat_possibility_result(true);
		} else {
			temp.setSimple_chat_possibility_result(false);
		}
		companyDao.updateSimpleChatPossibilityResult(temp);
	}

	public void updateChatPossibilityAll(String date, String slot_no, int MAX_CHAT_COUNT) {
		companyDao.updateChatPossibilityAll(date, slot_no, MAX_CHAT_COUNT);
		List<Company> list = companyDao.selectAll();
		for(int i=0; i < list.size(); i++)
		{
			Company temp = companyDao.select(list.get(i).getCompany_no());
			if (temp.getChat_possibility() && temp.isChat_switch()) {
				temp.setChat_possibility_result(true);
			} else {
				temp.setChat_possibility_result(false);
			}
			companyDao.updateChatPossibilityResult(temp);
		}
	}
	
	public void updateChatPossibility(Company company){
		companyDao.updateChatPossibility(company);
		Company temp = companyDao.select(company.getCompany_no());
		if(temp.getChat_possibility() && temp.isChat_switch())
		{
			temp.setChat_possibility_result(true);
		}
		else
		{
			temp.setChat_possibility_result(false);
		}
		companyDao.updateChatPossibilityResult(temp);
	}
	
	public void updateSimpleChatPossibility(Company company){
		companyDao.updateSimpleChatPossibility(company);
		Company temp = companyDao.select(company.getCompany_no());
		if(temp.isSimple_chat_possibility() && temp.isChat_switch())
		{
			temp.setSimple_chat_possibility_result(true);
		}
		else
		{
			temp.setSimple_chat_possibility_result(false);
		}
		companyDao.updateSimpleChatPossibilityResult(temp);
	}
	
	public void updateHistoryPossibilityCount(Company company){
		companyDao.updateHistoryPossibilityCount(company);
	}
	
	public void updateChatPossibilityResult(Company company){
		companyDao.updateChatPossibilityResult(company);
	}
	
	public void updateSimpleChatPossibilityResult(Company company){
		companyDao.updateSimpleChatPossibilityResult(company);
	}
	
	public void updateChatSwitch(Company company){
		companyDao.updateChatSwitch(company);
		Company temp = companyDao.select(company.getCompany_no());
		
		temp.setChat_possibility_result(temp.getChat_possibility() && temp.isChat_switch());
		temp.setSimple_chat_possibility_result(temp.isSimple_chat_possibility() && temp.isChat_switch());
		
		companyDao.updateAllChatPossibilityResult(temp);
	}
	
}
