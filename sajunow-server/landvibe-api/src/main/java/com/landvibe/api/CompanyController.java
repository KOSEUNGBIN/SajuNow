package com.landvibe.api;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.landvibe.api.response.ErrorResponse;
import com.landvibe.api.response.SuccessResponse;
import com.landvibe.common.utils.AuthUtils;
import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.companyquestion.CompanyQuestionBo;
import com.landvibe.core.companytocategory.CompanyToCategoryBo;
import com.landvibe.core.companytoreport.CompanyToReport;
import com.landvibe.core.companytoreport.CompanyToReportBo;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.report.Report;
import com.landvibe.core.report.ReportBo;
import com.landvibe.core.schedule.ScheduleBo;
import com.landvibe.core.user.UserBo;
import com.landvibe.core.userfavorite.UserFavorite;
import com.landvibe.core.userfavorite.UserFavoriteBo;

/**
 * 占쏙옙占쏙옙占쏙옙 api
 *
 */
@RestController
@RequestMapping("/company")
public class CompanyController {

	private static final String LOGOUT = "LOGOUT";
	private static final String BLOCK = "BLOCK";
	private static final String DUPLICATED = "DUPLICATED";

	public final static int MAX_CHAT_COUNT = 5;

	static public int getMAX_CHAT_COUNT() {
		return MAX_CHAT_COUNT;
	}

	@Autowired
	private CompanyBo companyBo;

	@Autowired
	private HistoryBo historyBo;

	@Autowired
	private ScheduleBo scheduleBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private ReportBo reportBo;

	@Autowired
	private UserFavoriteBo userFavoriteBo;

	@Autowired
	private CompanyToReportBo companyToReportBo;

	@Autowired
	private CompanyToCategoryBo companyToCategoryBo;

	@Autowired
	private CompanyQuestionBo companyQuestionBo;

	@Autowired
	private AuthUtils authUtils;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insertUser(@ModelAttribute Company company) {

		if (companyBo.selectByEmail(company.getEmail()) == null) {
			String result[];
			companyBo.create(company);

			result = company.getCategory_list().split("\\?");
			
			companyToCategoryBo.insert(0, company.getCompany_no());
			if(company.isHistory_simple())
				companyToCategoryBo.insert(1, company.getCompany_no());

			for (int i = 0; i < result.length; i++) {
				if (result[i].equals("true"))
					companyToCategoryBo.insert(i + 2, company.getCompany_no());
			}

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();

			for (int i = 0; i < 14; i++) {
				String date = dateFormat.format(calendar.getTime());

				scheduleBo.insertScheduleNewCompany(company.getCompany_no(), date);
				System.out.println(Thread.currentThread() + ": insertSchedule " + new Date());

				calendar.add(Calendar.DATE, 1);

			}
			return new SuccessResponse(true);
		} else {
			return new SuccessResponse(false);
		}

	}

	@RequestMapping(value = "/report/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insertReport(@ModelAttribute CompanyToReport companyToReport){

		History history = historyBo.getByNo(companyToReport.getCompany_no());
		Report report = new Report(companyToReport.getScore(), companyToReport.getComment(),userBo.getByNo(history.getUser_no()).getName());
		long report_no = reportBo.create(report);
		
		CompanyToReport TempCompanyToReport = new CompanyToReport(history.getCompany_no(), report_no);
		companyToReportBo.insert(TempCompanyToReport);

		Company company = new Company();
		company.setCompany_no(history.getCompany_no());
		company.setScore_average(companyBo.getSum(history.getCompany_no()));

		companyBo.updateScoreAverage(company);
		historyBo.updateIsAlarmed(history.getHistory_no() , 2);
				
		return new SuccessResponse(history);
	}

	@RequestMapping(value = "/complex", method = RequestMethod.POST)
	@ResponseBody
	public Object checkComplexedCompany(@ModelAttribute Company company){
		if (companyBo.selectByEmail(company.getEmail()) == null) {
			return new SuccessResponse(true);
		} else {
			return new SuccessResponse(false);
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void updatecompany(@ModelAttribute Company company) {
		companyBo.update(company);

	}

	@RequestMapping(value = "/update/possibility", method = RequestMethod.POST)
	@ResponseBody
	public void updateChatPosibility(@ModelAttribute Company company) {
		companyBo.updateChatSwitch(company);

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object Login(String email, String password, String company_reg_id, HttpServletResponse response)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		String company_token = "";
		Cookie CompanyKey;

		Company company = companyBo.Login(email, password);

		// true -> 로그인 성공 , false -> 이미 등록된 reg_id
		if (company.getCompany_reg_id().equals(LOGOUT) && userBo.selectByCompanyRegId(company_reg_id)
				&& companyBo.selectByUserRegId(company_reg_id)) {
			try {
				company_token = URLEncoder.encode(authUtils.generateToken2(company.getCompany_no(), email), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			CompanyKey = new Cookie("UserKey", company_token);
			// CompanyKey.setMaxAge(10000); // set expire time to 1000 sec
			response.addCookie(CompanyKey); // put cookie in response

			company.setCompany_token(company_token);
			companyBo.updateToken(company);

		} else if (company.getCompany_reg_id().equals(BLOCK))
		{
			return company;
		}
		else {
			company.setCompany_reg_id(DUPLICATED);
			return company;
		}

		return company;
	}

	@RequestMapping(value = "/change/userkey", method = RequestMethod.POST)
	@ResponseBody
	public Object ChangeUserKey(String email, String password, HttpServletResponse response)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		String user_token = "";
		Cookie UserKey;
		Company company;

		company = companyBo.Login(email, password);
		System.out.println(company.toString());
		try {
			user_token = URLEncoder.encode(authUtils.generateToken2(company.getCompany_no(), email), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (company != null) {
			UserKey = new Cookie("UserKey", user_token);
			response.addCookie(UserKey);

			company.setCompany_token(user_token);
			companyBo.updateToken(company);
		}
		System.out.println(company.toString());
		return company;
	}

	@RequestMapping(value = "/compare/regid", method = RequestMethod.POST)
	@ResponseBody
	public Object compareToken(String userkey, int company_no) {
		String companyRegId = companyBo.selectForBlock(company_no);

		if (companyRegId.equals(BLOCK)) {
			return new ErrorResponse("block");
		} else if (companyBo.selectByToken(userkey) > 0 && !companyRegId.equals(LOGOUT)) {
			return new SuccessResponse("success");
		} else {
			return new ErrorResponse("duplicate");
		}

	}

	@RequestMapping(value = "/update/regid", method = RequestMethod.POST)
	@ResponseBody
	public void updateRegId(@ModelAttribute Company company) {
		long company_no = company.getCompany_no();

		companyBo.updateRegId(company);

		List<History> companyHistoryList = historyBo.getByAllUserHistory(company.getCompany_no());

		for (int i = 0; i < companyHistoryList.size(); i++) {
			companyHistoryList.get(i).setCompany_reg(company.getCompany_reg_id());
			historyBo.updateCompanyReg(companyHistoryList.get(i));
		}
	}
	
	/**
	 * 占쏙옙占쏙옙占쏙옙 + 燁삳��占쏙옙�⑨옙�깍옙 筌��ㅿ옙占� �귐�占썬�쏙옙占� �븝옙占싼�占썬�븍┛ (占쏙옙占쎈��占�)
	 * 
	 * @return
	 */
	@RequestMapping("/join/category/all/{start:[0-9]+}")
	@ResponseBody
	public Object CompanyJoinCategoryPaging(@PathVariable long start) {
		List<Company> companyList = companyBo.getJoinCompany(start);
		for(int i =0; i < companyList.size(); i++)
		{
			companyList.get(i).setFavorite_count(userFavoriteBo.selectFavoriteCount(companyList.get(i).getCompany_no()));
			companyList.get(i).setReport_count(companyBo.getCompanyReportCount(companyList.get(i).getCompany_no()));
		}
		return companyList;
	}

	/**
	 * 燁삳��占쏙옙�⑨옙�귐�占� 筌��ㅿ옙占� 占쏙옙占쏙옙占쏙옙 + 燁삳��占쏙옙�⑨옙�깍옙 �귐�占썬�쏙옙占� �븝옙占싼�占썬�븍┛
	 * (占쏙옙占쎈��占�)
	 * 
	 * @return
	 */
	@RequestMapping("/join/category/{category_code:[0-9]+}/{start:[0-9]+}")
	@ResponseBody
	public Object CompanyJoinCategoryByCategoryPaging(@PathVariable long category_code, @PathVariable long start) {
		List<Company> companyList = companyBo.getJoinCompanyByCategory(start, category_code);
		
		return companyList;
	}

	// 占싼�占쏙옙 占쏙옙占쏙옙占쏙옙 占쎌�깍옙占� 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙 -> 占쏙옙占쏙옙占쏙옙 + 燁삳��占쏙옙�⑨옙�귐�占�
	// 占쏙옙筌ｏ옙
	@RequestMapping("/{companyNo:[0-9]+}")
	@ResponseBody
	public Object selectCompany(@PathVariable long companyNo) {
		Company company = companyBo.getByNo(companyNo);
		if (company == null) {
			return new ErrorResponse("占싼�占썩�뱄옙占쏙옙占썼��울옙占쏙옙占쎈�э옙占쏙옙占�.");
		} else {
			return new SuccessResponse(company);
		}
	}

	/**
	 * 占쏙옙占쏙옙占쏙옙 + 占쏙옙疫뀐옙 �븝옙占싼�占썬�븍┛
	 * 
	 * @param companyNo
	 * @return
	 */
	@RequestMapping("/join/report/{companyNo:[0-9]+}")
	@ResponseBody
	public Object CompanyJoinReport(@PathVariable long companyNo) {
		Company company = companyBo.getJoinCompanyToReport(companyNo);

		return company;
	}
	
	/**
	 * 占쏙옙占쏙옙占쏙옙 + 占쏙옙疫뀐옙 �븝옙占싼�占썬�븍┛
	 * 
	 * @param companyNo
	 * @return
	 */
	@RequestMapping("/join/category/{companyNo:[0-9]+}")
	@ResponseBody
	public Object CompanyJoinCategory(@PathVariable long companyNo) {
		Company company = companyBo.getJoinCompanyToCategoryByCompany(companyNo);

		return company;
	}


	@RequestMapping("/favorite/user")
	@ResponseBody
	public Object SelectFavorite(@ModelAttribute UserFavorite userFavorite) {
		List<UserFavorite> list = userFavoriteBo.select(userFavorite.getUser_no(), userFavorite.getCompany_no());
		if (list.size() == 0) {
			return new ErrorResponse(false);
		} else {
			return new SuccessResponse(true);
		}
	}

	@RequestMapping("/upload/image/{companyNo:[0-9]+}")
	@ResponseBody
	public Object UploadImage(@PathVariable long companyNo, HttpServletRequest request)
			throws IllegalStateException, IOException {
		final String filePath = "/home/oursoccer/www/company/";
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs(); // 筌�占쏙옙占쏙옙占쏙옙占썹㎉占쏙옙占� 占쎈��占쏙옙揶�占� 占쏙옙占쎌�겹��
							// 占쎈��占쏙옙占쏙옙占쏙옙
		}
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) {
				System.out.println(multipartFile.getName());
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = companyNo + ".png"; // 占쏙옙占썬�쏙옙占쏙옙�깍옙��占�+占쏙옙占싸쇽옙占�
														// (占쏙옙甕곤옙占쏙옙 占쏙옙占싸삼옙占�
														// 占쎈��占�)

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file); // 占쏙옙占쎌�깍옙占쏙옙占�

			} else
				System.out.println("multipartFile is Empty!!");
		}

		return storedFileName;

	}

	@RequestMapping("/image/{companyNo:[0-9]+}")
	@ResponseBody
	public HttpEntity<byte[]> getPhoto(@PathVariable Long companyNo) {
		byte[] image = null;
		try {
			image = org.apache.commons.io.FileUtils
					.readFileToByteArray(new File("/home/oursoccer/www/company/" + companyNo + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				image = org.apache.commons.io.FileUtils
						.readFileToByteArray(new File("/home/oursoccer/www/company/" + "profile_default" + ".jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}
	
	@RequestMapping("/outerjoin/user/join/category/{user_no:[0-9]+}/simple/{offset:[0-9]+}/{limit:[0-9]+}")
	@ResponseBody
	public Object CompanySimpleCategoryAll(@PathVariable long user_no, @PathVariable int offset,
			@PathVariable int limit) {
		List<Company> companyList = companyBo.getAllSimpleCategory();
		return companyList;
	}

	@RequestMapping("/join/user/join/category/{user_no:[0-9]+}/all")
	@ResponseBody
	public Object CompanyjoinUserJoinCategoryAll(@PathVariable long user_no) {
		List<Company> companyList = companyBo.getAllCompanyjoinUserjoinCategory(user_no);
		for(int i =0; i < companyList.size(); i++)
		{
			companyList.get(i).setFavorite_count(userFavoriteBo.selectFavoriteCount(companyList.get(i).getCompany_no()));
			companyList.get(i).setReport_count(companyBo.getCompanyReportCount(companyList.get(i).getCompany_no()));
		}
		return companyList;
	}

	@RequestMapping("/outerjoin/user/join/category/{user_no:[0-9]+}/all/{offset:[0-9]+}/{limit:[0-9]+}")
	@ResponseBody
	public Object CompanyOuterjoinUserJoinCategoryAll(@PathVariable long user_no, @PathVariable int offset,
			@PathVariable int limit) {
		List<Company> companyList = companyBo.getAllCompanyOuterjoinUserjoinCategory(user_no, offset, limit);
		return companyList;
	}

	@RequestMapping("/outerjoin/user/join/category/{user_no:[0-9]+}/{category_code:[0-9]+}/{offset:[0-9]+}/{limit:[0-9]+}")
	@ResponseBody
	public Object CompanyOuterjoinUserJoinCategory(@PathVariable long user_no, @PathVariable int category_code,
			@PathVariable int offset, @PathVariable int limit) {
		List<Company> companyList = companyBo.getCompanyOuterjoinUserjoinCategory(user_no, category_code, offset,
				limit);
		return companyList;
	}

	@RequestMapping("/question")
	@ResponseBody
	public Object selectUserQuestionRegisterDate() {
		return new SuccessResponse(companyQuestionBo.getRegisterDateList());
	}
	
}
