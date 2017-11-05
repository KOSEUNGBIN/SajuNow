package com.landvibe.api;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

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

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import com.landvibe.api.response.ErrorResponse;
import com.landvibe.api.response.SuccessResponse;
import com.landvibe.common.send.SendEmail;
import com.landvibe.common.send.SendGcm;
import com.landvibe.core.chatmessage.ChatMessage;
import com.landvibe.core.chatmessage.ChatMessageBo;
import com.landvibe.core.company.Company;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.companyquestion.CompanyQuestionBo;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.pay.Payment;
import com.landvibe.core.pay.PaymentBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;
import com.landvibe.core.userquestion.UserQuestionBo;

/**
 * 대화 기록 api
 * 
 */
@RestController
@RequestMapping("/history")
public class HistoryController {

	private static final String LOGOUT = "LOGOUT";

	private static final String BLOCK = "BLOCK";

	@Autowired
	private HistoryBo historyBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private CompanyBo companyBo;
	
	@Autowired
	private PaymentBo paymentBo;

	@Autowired
	private ChatMessageBo chatMessageBo;

	@Autowired
	private UserQuestionBo userQuestionBo;

	@Autowired
	private CompanyQuestionBo companyQuestionBo;

	@Autowired
	SendEmail sendEmail;

	@Autowired
	SendGcm sendGcm;

	/**
	 * history생성(채팅 시작 때 생성 end_date 제외)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/insert/general", method = RequestMethod.POST)
	@ResponseBody
	public Object insertGeneralHistory(@ModelAttribute History history) {
		
		User user = userBo.getByNo(history.getUser_no());
		Company company = companyBo.getByNo(history.getCompany_no());

		String userRegId = user.getUser_reg_id();
		String companyRegId = company.getCompany_reg_id();

		history.setUser_reg_id(userRegId);
		history.setCompany_reg_id(companyRegId);

		try {
			// 해당 유저가 없고
			// 역술인이 채팅가능 하고 로그인시에만 채팅방이 개설된다.
			// 역술인의 < 로그인, 로그 아웃, BLOCK, NonBLOCK > 상태는
			// company.isChat_possibility_result()에 포함된다.
			if (company.isChat_possibility_result() && !userRegId.equals(BLOCK)
					&& !companyRegId.equals(LOGOUT)) {

				long history_no = -1L;
				history_no = historyBo.create(history);

				// history_no가 존재할 때
				// 즉, 채팅방이 개설되었을 때
				// GCM 을 보내고 message를 DB에 저장한다.
				// 상담 횟수와 상담 가능 횟수도 Update한다.
				if (history_no != -1) {

					// GCM은 역술인에게 보내어 상담이 시작됨을 알리고 
					// ChatMessage의 DB 저장은 역술인의 상담 인삿말(history_introduce)을 저장한다. 
					
					// GCM 으로 역술인에게 인삿말 push
					String message = company.getNick_name() + "님 안녕하세요."; // 인삿말

					// ChatMessage -> DB 에 저장 -- 역술인의 상담 인삿말로 시작
					chatMessageBo.create(new ChatMessage(history_no,company.getHistory_introduce() , 10)); 

					// GCM 을 보낸다.
					sendGcm.send(history_no, message, 0, false, user.getName(),history.getSelect_history() , true);
					
					// Schedule
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
					String date = dateFormat.format(new Date(System.currentTimeMillis()));
					String slot_no = "slot_" + timeFormat.format(new Date(System.currentTimeMillis()));

					// 상담 중인 횟수 , 상담 가능성 -> UPDATE
					companyBo.updateChatCountAndChatPossibility(company.getCompany_no(), "INCREASE", date, slot_no);
					Payment payment_update = new Payment(user.getUser_no(), company.getCompany_no(), history_no, user.getEmail(),company.getmargin());
					paymentBo.updateBase(payment_update);

					return new SuccessResponse(history_no);
				}
				// history_no가 없을때
				// 채팅방이 개설되지 않았을 때
				else {
					sendEmail.sendNaverEmail("kosb1563@gmail.com");
					System.out.println("전문 사주 채팅방 접속 오류가 일어났습니다.");
					return new ErrorResponse("error");
				}
			}
			// 관리자가 User를 BLOCK 상태로 막아 놓았을 시
			// -100을 리턴한다.
			else if (userRegId.equals(BLOCK)) {
				return new SuccessResponse(-100);
			}
			// 역술인이 상담 불가 상태에서
			else {
				// 부재중
				// Chat_switch -> false 이거나
				// 역술인의 RegId가 BLOCK 이나 LOGOUT일 경우에 -10 을 리턴한다.
				if (!company.isChat_switch() || companyRegId.equals(BLOCK) || companyRegId.equals(LOGOUT)) {
					return new SuccessResponse(-10);
				}
				// 상담중
				// 상담 중인 횟수가 상담 가능 횟수보다 크거나 같을 때 -1 을 리턴한다.
				else {
					return new SuccessResponse(-1);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ErrorResponse(e);
		}

	}
	
	/**
	 * history생성(채팅 시작 때 생성 end_date 제외)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/status/general", method = RequestMethod.POST)
	@ResponseBody
	public Object getHistoryGeneralStatus(@ModelAttribute History history) {
	
		User user = userBo.getByNo(history.getUser_no());
		Company company = companyBo.getByNo(history.getCompany_no());

		History historyTemp = historyBo.getByhistoryEndYnGeneral(history.getUser_no(), history.getCompany_no());

		String userRegId = user.getUser_reg_id();
		String companyRegId = company.getCompany_reg_id();

		history.setUser_reg_id(userRegId);
		history.setCompany_reg_id(companyRegId);

		try {
			// 해당 유저가 없고
			// 역술인이 채팅가능 하고 로그인시에만 채팅방이 개설된다.
			// 역술인의 < 로그인, 로그 아웃, BLOCK, NonBLOCK > 상태는
			// company.isChat_possibility_result()에 포함된다.
			if (historyTemp == null && company.isChat_possibility_result() && !userRegId.equals(BLOCK)
					&& !companyRegId.equals(LOGOUT)) {
					return new SuccessResponse(-1000);
			}
			// 해당 유저와 컴패니의 채팅이 존재할 때
			// 해당 개설되어 있는 채팅방의 history_no 를 리턴한다.
			else if (historyTemp != null) {
				if(historyTemp.getSelect_history() == history.getSelect_history())
					return new SuccessResponse(historyTemp.getHistory_no());
				else
					return new SuccessResponse(historyTemp.getSelect_history() * -1);
			}
			// 관리자가 User를 BLOCK 상태로 막아 놓았을 시
			// -100을 리턴한다.
			else if (userRegId.equals(BLOCK)) {
				return new SuccessResponse(-100);
			}
			// 역술인이 상담 불가 상태에서
			else {
				// 부재중
				// Chat_switch -> false 이거나
				// 역술인의 RegId가 BLOCK 이나 LOGOUT일 경우에 -10 을 리턴한다.
				if (!company.isChat_switch() || companyRegId.equals(BLOCK) || companyRegId.equals(LOGOUT)) {
					return new SuccessResponse(-10);
				}
				// 상담중
				// 상담 중인 횟수가 상담 가능 횟수보다 크거나 같을 때 -1 을 리턴한다.
				else {
					return new SuccessResponse(-1);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ErrorResponse(e);
		}

	}

	/**
	 * history생성(채팅 시작 때 생성 end_date 제외)
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/insert/simple", method = RequestMethod.POST)
	@ResponseBody
	public Object insertSimpleHistory(@ModelAttribute History history, @RequestParam("message") String message) {
		// History history = new History(0,3,4,true,true,"2015-10-11
		// 00:01:02",null,null); //임시데이터

		User user = userBo.getByNo(history.getUser_no());
		Company company = companyBo.getByNo(history.getCompany_no());

		String userRegId = user.getUser_reg_id();
		String companyRegId = company.getCompany_reg_id();

		history.setUser_reg_id(userRegId);
		history.setCompany_reg_id(companyRegId);
		try {
			// 해당 유저가 없고
			// 역술인이 채팅가능 하고 로그인시에만 채팅방이 개설된다.
			// 역술인의 < 로그인, 로그 아웃, BLOCK, NonBLOCK > 상태는
			// company.isChat_possibility_result()에 포함된다.
			if (company.isSimple_chat_possibility_result() && !userRegId.equals(BLOCK)
					&& !companyRegId.equals(LOGOUT)) {
				long history_no = -1L;
				history_no = historyBo.create(history);

				// history_no가 존재할 때
				// 즉, 채팅방이 개설되었을 때
				// GCM 을 보내고 message를 DB에 저장한다.
				// 상담 횟수와 상담 가능 횟수도 Update한다.
				if (history_no != -1) {

					// GCM Message -> DB 에 저장
					// chatMessageBo.create(new
					// ChatMessage(history_no,company.getNick_name() + "\n간단 사주
					// 상담을 부탁드려요~", 0));
					chatMessageBo.create(new ChatMessage(history_no, message, 0));

					// GCM 을 보낸다.
					sendGcm.send(history_no, message, 0, false, user.getName(), history.getSelect_history(), true);

					// Schedule
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat timeFormat = new SimpleDateFormat("HH");
					String date = dateFormat.format(new Date(System.currentTimeMillis()));
					String slot_no = "slot_" + timeFormat.format(new Date(System.currentTimeMillis()));

					// 상담 중인 횟수 , 상담 가능성 -> UPDATE
					companyBo.updateSimpleChatCountAndChatPossibility(company.getCompany_no(), "INCREASE", date,
							slot_no);

					historyBo.updateSimpleEndyn(history_no);
					Payment payment_update = new Payment(user.getUser_no(), company.getCompany_no(), history_no, user.getEmail(), company.getmargin());
					paymentBo.updateBase(payment_update);

					return new SuccessResponse(history_no);
				}
				// history_no가 없을때
				// 채팅방이 개설되지 않았을 때
				else {
					sendEmail.sendNaverEmail("kosb1563@gmail.com");
					System.out.println("간단 사주 채팅방 접속 오류가 일어났습니다.");
					return new ErrorResponse("error");
				}
			}		
			// 관리자가 User를 BLOCK 상태로 막아 놓았을 시
			// -100을 리턴한다.
			else if (userRegId.equals(BLOCK)) {
				return new SuccessResponse(-100);
			}
			// 역술인이 상담 불가 상태에서
			else {
				// 부재중
				// Chat_switch -> false 이거나
				// 역술인의 RegId가 BLOCK 이나 LOGOUT일 경우에 -10 을 리턴한다.
				if (!company.isChat_switch() || companyRegId.equals(BLOCK) || companyRegId.equals(LOGOUT)) {
					return new SuccessResponse(-10);
				}
				// 상담중
				// 상담 중인 횟수가 상담 가능 횟수보다 크거나 같을 때 -1 을 리턴한다.
				else {
					return new SuccessResponse(-1);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ErrorResponse(e);
		}
	}

	/**
	 * 간단 사주 가능성 비교
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/status/simple", method = RequestMethod.POST)
	@ResponseBody
	public Object compareSimpleHistory(@ModelAttribute History history) {
		
		User user = userBo.getByNo(history.getUser_no());
		Company company = companyBo.getByNo(history.getCompany_no());

		History historyTemp = historyBo.getByhistoryEndYnSimple(history.getUser_no(), history.getCompany_no());

		String userRegId = user.getUser_reg_id();
		String companyRegId = company.getCompany_reg_id();

		try {
			// 상담이 가능할 시에만 -1000을 리턴한다.
			if (historyTemp == null && company.isSimple_chat_possibility_result() && !userRegId.equals(BLOCK)
					&& !companyRegId.equals(LOGOUT)) {
				return new SuccessResponse(-1000);
				
			}
			// 해당 유저와 컴패니의 채팅이 존재할 때
			// 해당 개설되어 있는 채팅방의 history_no 를 리턴한다.
			else if (historyTemp != null) {
				return new SuccessResponse(historyTemp.getHistory_no());
			}
			// 관리자가 User를 BLOCK 상태로 막아 놓았을 시
			// -100을 리턴한다.
			else if (userRegId.equals(BLOCK)) {
				return new SuccessResponse(-100);
			}
			// 역술인이 상담 불가 상태에서
			else {
				// 부재중
				// Chat_switch -> false 이거나
				// 역술인의 RegId가 BLOCK 이나 LOGOUT일 경우에 -10 을 리턴한다.
				if (!company.isChat_switch() || companyRegId.equals(BLOCK) || companyRegId.equals(LOGOUT)) {
					return new SuccessResponse(-10);
				}
				// 상담중
				// 상담 중인 횟수가 상담 가능 횟수보다 크거나 같을 때 -1 을 리턴한다.
				else {
					return new SuccessResponse(-1);
				}
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ErrorResponse(e);
		}

	}

	/**
	 * user_no로 상담 중인 채팅방 개수를 조회하여 return 한다.
	 * 
	 * @param user_no
	 */
	@RequestMapping(value = "/consert/count/user/{user_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public Object selectUserConsertCount(@PathVariable long user_no) {

		Map<String, Long> map = new HashMap<String, Long>();

		if (userQuestionBo.getRegisterDateList().size() > 0)
			map.put("information_new", 1L);
		else
			map.put("information_new", -1L);

		long count = historyBo.getByUserConsertCount(user_no);

		if (count < 0) {
			map.put("count", -1L);
			return new ErrorResponse(map);
		} else {
			map.put("count", count);
			return new SuccessResponse(map);
		}
	}

	/**
	 * company_no로 상담 중인 채팅방 개수를 조회하여 return 한다.
	 * 
	 * @param company_no
	 */
	@RequestMapping(value = "/consert/count/company/{company_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public Object selectCompanyConsertCount(@PathVariable long company_no) {

		Map<String, Long> map = new HashMap<String, Long>();

		if (companyQuestionBo.getRegisterDateList().size() > 0)
			map.put("information_new", 1L);
		else
			map.put("information_new", -1L);

		long count = historyBo.getByCompanyConsertCount(company_no);

		if (count < 0) {
			map.put("count", -1L);
			return new ErrorResponse(map);
		} else {
			map.put("count", count);
			return new SuccessResponse(map);
		}
	}

	/**
	 * history update user reg id, 안읽은 메세지 초기화
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/update/user", method = RequestMethod.POST)
	@ResponseBody
	public void updateUserReg(@ModelAttribute History history) {

		// 안읽은 메세지 초기화
		historyBo.countInitUser(history.getHistory_no());

		// 채팅방에서 user의 reg id를 갱신
		User user = userBo.getByNo(history.getUser_no());
		history.setUser_reg_id(user.getUser_reg_id());
		historyBo.updateUserReg(history);

	}

	/**
	 * history update company reg id, 안읽은 메세지 초기화
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/update/company", method = RequestMethod.POST)
	@ResponseBody
	public void updateCompanyReg(@ModelAttribute History history) {

		// 안읽은 메세지 초기화
		historyBo.countInitCompany(history.getHistory_no());

		// 채팅방에서 company의 reg id를 갱신
		Company company = companyBo.getByNo(history.getCompany_no());
		history.setCompany_reg_id(company.getCompany_reg_id());
		historyBo.updateCompanyReg(history);

	}

	@RequestMapping(value = "/update/alarmed", method = RequestMethod.POST)
	@ResponseBody
	public void updateAlarmed(@RequestParam("history_no") long history_no , @RequestParam("is_report_alarmed") long is_report_alarmed) {
		historyBo.updateIsAlarmed(history_no , is_report_alarmed);
	}

	@RequestMapping(value = "/update/simple/endyn", method = RequestMethod.POST)
	@ResponseBody
	public void updateSimpleEndyn(@RequestParam("history_no") long history_no) {

		historyBo.updateSimpleEndyn(history_no);

	}

	@RequestMapping(value = "/delete/user/{history_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public void deleteUserHistory(@PathVariable long history_no) {

		History history = historyBo.getByNo(history_no);

		if (!(history.isIsdeleted_user() || history.isIsdeleted_company()))
			historyBo.updateIsdelete_user(history_no);// user의
														// isIsdeleted_user()를
														// 1로 업데이트
		else if (!history.isIsdeleted_user() && history.isIsdeleted_company())
			historyBo.delete(history_no);// 해당 history_no의 히스토리를 delete
		else
			;
	}

	@RequestMapping(value = "/delete/company/{history_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public void deleteCompanyHistory(@PathVariable long history_no) {

		History history = historyBo.getByNo(history_no);

		if (!(history.isIsdeleted_user() || history.isIsdeleted_company()))
			historyBo.updateIsdelete_company(history_no);// history의
															// isIsdeleted_company를
															// true로 업데이트
		else if (history.isIsdeleted_user() && !history.isIsdeleted_company())
			historyBo.delete(history_no);// 해당 history_no 히스토리를 delete
		else
			;
	}

	/**
	 * User app 안읽은 메세지 초기화
	 * 
	 * @param history_no
	 */
	@RequestMapping(value = "/init/user/{history_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public void countInitUser(@PathVariable long history_no) {
		// 안읽은 메세지 초기화
		historyBo.countInitUser(history_no);
	}

	/**
	 * Company app 안읽은 메세지 초기화
	 * 
	 * @param history_no
	 */
	@RequestMapping(value = "/init/company/{history_no:[0-9]+}", method = RequestMethod.POST)
	@ResponseBody
	public void countInitCompany(@PathVariable long history_no) {
		// 안읽은 메세지 초기화
		historyBo.countInitCompany(history_no);
	}

	/**
	 * history 끝 시간 입력 (채팅 끝날 때 실행)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/end", method = RequestMethod.POST)
	@ResponseBody
	public void endHistory(@ModelAttribute History history) {
		// History history = new History(0,3,4,true,true,"2015-10-11
		// 00:01:02","2015-10-12 00:01:02",null); //임시데이터
		historyBo.insertEnd(history);
	}

	/**
	 * 상담후기, 대화내용 가져오기
	 * 
	 * @return
	 */
	@RequestMapping("/{history_no:[0-9]+}")
	@ResponseBody
	public Object selectHsitory(@PathVariable long history_no) {

		History history = historyBo.getByNo(history_no);

		if (history == null) {
			return new ErrorResponse("대화내역이 없습니다.");
		} else {
			return new SuccessResponse(history);
		}
	}

	/**
	 * User app에서 접근, user_no에 해당하는 history List를 불러온다.
	 * 
	 * @param userNo
	 * @return List<History>
	 */
	@RequestMapping(value = "/allcompany/{userNo:[0-9]+}")
	@ResponseBody
	public Object selectHistoryCompanyAll(@PathVariable long userNo) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<History> companyHistoryList = historyBo.getByAllCompanyHistory(userNo);
		map.put("list", companyHistoryList);

		// 받는부분이랑 같이 변경 예정
		Iterator<History> iter = companyHistoryList.iterator();
		long total = 0;
		while (iter.hasNext()) {
			total += iter.next().getUser_not_read();
		}
		map.put("NotReadAll", total);

		if (map.isEmpty()) {
			return new ErrorResponse("대화 기록이 없습니다!!!!");
		} else {
			return new SuccessResponse(map);
		}

	}

	/**
	 * Company app에서 접근, company_no에 해당하는 history List를 불러온다.
	 * 
	 * @param CompanyNo
	 * @return List<History>
	 */
	@RequestMapping(value = "/alluser/{companyNo:[0-9]+}")
	@ResponseBody
	public Object selectHistoryUserAll(@PathVariable long companyNo) {
		Map<String, Object> map = new HashMap<String, Object>();

		List<History> userHistoryList = historyBo.getByAllUserHistory(companyNo);
		map.put("list", userHistoryList);

		// 받는부분이랑 같이 변경 예정
		Iterator<History> iter = userHistoryList.iterator();
		long total = 0;
		while (iter.hasNext()) {
			total += iter.next().getCompany_not_read();
		}
		map.put("NotReadAll", total);

		if (map.isEmpty()) {
			return new ErrorResponse("대화 기록이 없습니다!!!!");
		} else {
			return new SuccessResponse(map);
		}

	}

	@RequestMapping("/upload/image/company/{historyNo:[0-9]+}")
	@ResponseBody
	public Object UploadImage(@PathVariable long historyNo, HttpServletRequest request)
			throws IllegalStateException, IOException {

		final String filePath = "/home/oursoccer/www/history/";
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdfNow.format(new Date(System.currentTimeMillis()));

		// 사용자 단말기 RegistrationId
		// String regId =
		// "APA91bF_d3xeHa0MbPPWnKKUBoggVNk4totvWV9mi5uDFSw-XBxid7WY7oT9pt597zRm5ziPj7yaPzN6zvD7izOtjmdlJ0srHloYuMMfMwo6A_09Dg6RNaeLv7-e1Qt9pBQtEWeXQeZU";

		History history = historyBo.getHistory(historyNo);
		String company_name = companyBo.getByNo(history.getCompany_no()).getNick_name();

		// GCM 발송
		sendGcm.send(historyNo, "image", 11, false, company_name, history.getSelect_history(), false);

		ChatMessage chatMessage = new ChatMessage(historyNo, "image", 11, time);
		chatMessageBo.create(chatMessage);
		long msg_no = chatMessage.getMessage_no();
		System.out.println("msg_no : " + msg_no);

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
				storedFileName = historyNo + "_" + msg_no + ".png"; // 占쏙옙占썬�쏙옙占쏙옙�깍옙��占�+占쏙옙占싸쇽옙占�
				// (占쏙옙甕곤옙占쏙옙 占쏙옙占싸삼옙占� 占쎈��占�)

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file); // 占쏙옙占쎌�깍옙占쏙옙占�

			}
		}

		return storedFileName;

	}

	@RequestMapping("/upload/image/user/{historyNo:[0-9]+}")
	@ResponseBody
	public Object UploadUserImage(@PathVariable long historyNo, HttpServletRequest request)
			throws IllegalStateException, IOException {

		final String filePath = "/home/oursoccer/www/history/";
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdfNow.format(new Date(System.currentTimeMillis()));

		History history = historyBo.getHistory(historyNo);
		String user_name = userBo.getByNo(history.getUser_no()).getName();

		// GCM 발송
		sendGcm.send(historyNo, "image", 1, false, user_name, history.getSelect_history(), false);

		ChatMessage chatMessage = new ChatMessage(historyNo, "image", 1, time);
		chatMessageBo.create(chatMessage);
		long msg_no = chatMessage.getMessage_no();
		System.out.println("msg_no : " + msg_no);

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs();
		}
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) {
				System.out.println(multipartFile.getName());
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = historyNo + "_" + msg_no + ".png";
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);

			}
		}

		return storedFileName;

	}

	@RequestMapping("/image/{historyNo:[0-9]+}/{position:[0-9]+}")
	@ResponseBody
	public HttpEntity<byte[]> getPhoto(@PathVariable long historyNo, @PathVariable long position) {
		byte[] image = null;
		try {
			image = org.apache.commons.io.FileUtils.readFileToByteArray(
					new File("/home/oursoccer/www/history/" + historyNo + "_" + position + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		headers.setContentLength(image.length);
		return new HttpEntity<byte[]>(image, headers);
	}

}
