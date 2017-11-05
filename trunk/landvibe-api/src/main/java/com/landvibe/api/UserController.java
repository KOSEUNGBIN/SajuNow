package com.landvibe.api;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.landvibe.api.response.ErrorResponse;
import com.landvibe.api.response.SuccessResponse;
import com.landvibe.common.utils.AuthUtils;
import com.landvibe.core.company.CompanyBo;
import com.landvibe.core.friend.Friend;
import com.landvibe.core.friend.FriendBo;
import com.landvibe.core.history.History;
import com.landvibe.core.history.HistoryBo;
import com.landvibe.core.user.User;
import com.landvibe.core.user.UserBo;
import com.landvibe.core.userfavorite.UserFavoriteBo;
import com.landvibe.core.userquestion.UserQuestion;
import com.landvibe.core.userquestion.UserQuestionBo;

/**
 * �ъ�⑹�� api
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final String LOGOUT = "LOGOUT";
	private static final String BLOCK = "BLOCK";
	private static final String DUPLICATED = "DUPLICATED";
	@Autowired
	private HistoryBo historyBo;

	@Autowired
	private UserBo userBo;

	@Autowired
	private CompanyBo companyBo;

	@Autowired
	private FriendBo friendBo;

	@Autowired
	private UserFavoriteBo userFavoriteBo;
	
	@Autowired
	private UserQuestionBo userQuestionBo;

	@Autowired
	private AuthUtils authUtils;

	/**
	 * �ъ�⑹�� ��蹂� 遺��ъ�ㅺ린
	 * 
	 * @param userNo
	 * @return
	 */
	@RequestMapping("/{userNo:[0-9]+}")
	@ResponseBody
	public Object users(@PathVariable long userNo) {
		User user = userBo.getByNo(userNo);
		return user;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insertUser(@ModelAttribute User user) {
		User userByEmail = userBo.selectByEmail(user.getEmail());
		
		if (userByEmail == null) { //셋중하나로 존재하는가?
			long user_no = -1L;
			user_no = userBo.create(user);
			return new SuccessResponse(user_no);
		} 
		else { //이미 메일이 존재함
			long userNoByEmail = userByEmail.getUser_no();
			user.setUser_no(userNoByEmail);
			if(user.getFacebook_id().equals("-1") && user.getNaver_id().equals("-1")) // 일반 회원가입 요청 -> password update
			{
				userBo.updatePassword(user);
			}
			else if(!user.getFacebook_id().equals("-1") && user.getNaver_id().equals("-1")) // 페이스북 회원가입 요청 -> facebook_id update
			{
				userBo.updateFacebookId(user);
			}
			else																			// 네이버 회원가입 요청 -> naver_id update
			{
				userBo.updateNaverId(user);
			}
			return new ErrorResponse(userNoByEmail);
		}

	}

	@RequestMapping(value = "/complex", method = RequestMethod.POST)
	@ResponseBody
	public Object checkComplexedUser(@ModelAttribute User user) {

		if (userBo.selectByEmail(user.getEmail()) == null) {
			return new SuccessResponse(true);
		} else {
			return new SuccessResponse(false);
		}
	}

	/**
	 * �ъ�⑹�� ��蹂� 遺��ъ�ㅺ린
	 * 
	 * @param userNo
	 * @return
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object Login(String email, String password, String user_reg_id, HttpServletResponse response)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		String user_token = "";
		Cookie UserKey;
		User user;

		user = userBo.selectForLogin(email, password);
		
		if(Integer.parseInt(user.getNaver_id()) < 0 )
		{

		try {
			if (user.getUser_reg_id().equals(LOGOUT) && companyBo.selectByUserRegId(user_reg_id)
					&& userBo.selectByCompanyRegId(user_reg_id)) {
				try {
					user_token = URLEncoder.encode(authUtils.generateToken2(user.getUser_no(), email), "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (user != null) {
					UserKey = new Cookie("UserKey", user_token);
					// UserKey.setMaxAge(10000); //set expire time to 1000 sec
					response.addCookie(UserKey); // put cookie in response

					user.setUser_token(user_token);
					userBo.updateToken(user);
					user.setUnalarmed_report_count((int) historyBo.selectUnAlarmedReportCount(user.getUser_no()));
				}

			} else if (user.getUser_reg_id().equals(BLOCK)) {
				return user;
			}

			else {
				user.setUser_reg_id(DUPLICATED);
				return user;
			}

		} catch (NullPointerException e) {
			return new ErrorResponse("로그인 정보가 올바르지 않습니다.");
		}
		}
		else
		{
			return new ErrorResponse("로그인 정보가 올바르지 않습니다.");
		}

		return user;
	}
	
	@RequestMapping(value = "/change/userkey/naver", method = RequestMethod.POST)
	@ResponseBody
	public Object ChangeUserKeyForNaver(long user_no, String email, HttpServletResponse response)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		String user_token = "";
		Cookie UserKey;
		User user = new User();
		user.setUser_no(user_no);


		try {
			user_token = URLEncoder.encode(authUtils.generateToken2(user_no, email), "UTF-8");
			System.out.println(user_token);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

			UserKey = new Cookie("UserKey", user_token);
			response.addCookie(UserKey);
			user.setUser_token(user_token);
			userBo.updateToken(user);
			user.setUnalarmed_report_count((int) historyBo.selectUnAlarmedReportCount(user_no));
			
		return user;
	}
	

	@RequestMapping(value = "/change/userkey", method = RequestMethod.POST)
	@ResponseBody
	public Object ChangeUserKey(String email, String password, HttpServletResponse response)
			throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException {

		String user_token = "";
		Cookie UserKey;
		User user;

		user = userBo.selectForLogin(email, password);

		try {
			user_token = URLEncoder.encode(authUtils.generateToken2(user.getUser_no(), email), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (user != null) {
			UserKey = new Cookie("UserKey", user_token);
			response.addCookie(UserKey);

			user.setUser_token(user_token);
			userBo.updateToken(user);
			user.setUnalarmed_report_count((int) historyBo.selectUnAlarmedReportCount(user.getUser_no()));
		}

		return user;
	}

	@RequestMapping(value = "/update/regid", method = RequestMethod.POST)
	@ResponseBody
	public void updateRegId(@ModelAttribute User user) {
		userBo.updateRegId(user);
		List<History> companyHistoryList = historyBo.getByAllCompanyHistory(user.getUser_no());

		for (int i = 0; i < companyHistoryList.size(); i++) {
			companyHistoryList.get(i).setUser_reg_id(user.getUser_reg_id());
			historyBo.updateUserReg(companyHistoryList.get(i));
		}
	}

	@RequestMapping(value = "/compare/regid", method = RequestMethod.POST)
	@ResponseBody
	public Object compareToken(String userkey, int user_no) {

		String userRegId = userBo.selectForBlock(user_no);
		
		if (userRegId.equals(BLOCK)) {
			return new ErrorResponse("block");
		} else if (userBo.selectByToken(userkey) > 0 && !userRegId.equals(LOGOUT)) {
			return new SuccessResponse("success");
		} else {
			return new ErrorResponse("duplicate");
		}
	}
	
	@RequestMapping(value = "/login/naver", method = RequestMethod.POST)
	@ResponseBody
	public Object loginNaver(@ModelAttribute User user,HttpServletResponse response) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		User userDB = userBo.selectUserRegIdByNaverId(user.getNaver_id());
		boolean flag = true;
		
		
		try {
			flag = userDB.getUser_reg_id().equals("");      //등록된 네이버아이디가 없는 경우   -> 네이버 아이디로 추가 가입 절차 진행
		}
		catch(Exception e){
			return new ErrorResponse("EMPTY");
		}
		
			
								//등록된 네이버아이디가 있는 경우 -> 토큰
		
			user.setUser_no(userDB.getUser_no());
			String user_token = null;
			if (userDB.getUser_reg_id().equals(LOGOUT)) {
			try {
				user_token = URLEncoder.encode(authUtils.generateToken2(userDB.getUser_no(), userDB.getEmail()), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				Cookie UserKey = new Cookie("UserKey", user_token);
				// UserKey.setMaxAge(10000); //set expire time to 1000 sec
				response.addCookie(UserKey); // put cookie in response

				user.setUser_token(user_token);
				userBo.updateToken(user);
				user.setUnalarmed_report_count((int) historyBo.selectUnAlarmedReportCount(userDB.getUser_no()));
			
			user.setUser_reg_id(userDB.getUser_reg_id());
			return new SuccessResponse(user); //regid, Userkey
			}
		 else if (userDB.getUser_reg_id().equals(BLOCK)) {
			return new SuccessResponse(user);
		}

		else {
			user.setUser_reg_id(DUPLICATED);
			return new SuccessResponse(user);
		}
	}
	
	@RequestMapping(value = "/login/facebook", method = RequestMethod.POST)
	@ResponseBody
	public Object loginFacebook(@ModelAttribute User user,HttpServletResponse response) throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		User userDB = userBo.selectUserRegIdByFacebookId(user.getFacebook_id());
		boolean flag = true;
		
		
		try {
			flag = userDB.getUser_reg_id().equals("");      //등록된 네이버아이디가 없는 경우   -> 네이버 아이디로 추가 가입 절차 진행
		}
		catch(Exception e){
			return new ErrorResponse("EMPTY");
		}
		
			
								//등록된 페이스북 아이디가 있는 경우 -> 토큰
		
			user.setUser_no(userDB.getUser_no());
			String user_token = null;
			if (userDB.getUser_reg_id().equals(LOGOUT)) {
			try {
				user_token = URLEncoder.encode(authUtils.generateToken2(userDB.getUser_no(), userDB.getEmail()), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				Cookie UserKey = new Cookie("UserKey", user_token);
				// UserKey.setMaxAge(10000); //set expire time to 1000 sec
				response.addCookie(UserKey); // put cookie in response

				user.setUser_token(user_token);
				userBo.updateToken(user);
				user.setUnalarmed_report_count((int) historyBo.selectUnAlarmedReportCount(userDB.getUser_no()));
			
			user.setUser_reg_id(userDB.getUser_reg_id());
			return new SuccessResponse(user); //regid, Userkey
			}
		 else if (userDB.getUser_reg_id().equals(BLOCK)) {
			return new SuccessResponse(user);
		}

		else {
			user.setUser_reg_id(DUPLICATED);
			return new SuccessResponse(user);
		}
	}

	/**
	 * 紐⑤�� �ъ�⑹�� ��蹂� 遺��ъ�ㅺ린
	 * 
	 * @return
	 */
	@RequestMapping("/all")
	@ResponseBody
	public Object allUsers() {
		List<User> userList = userBo.getAll();
		return userList;
	}

	/**
	 * �ъ�⑹�� + 利�寃⑥갼湲� 紐⑤�� 由ъ�ㅽ�� 遺��ъ�ㅺ린
	 * 
	 * @return
	 */
	@RequestMapping("/join/favorite")
	@ResponseBody
	public Object joinUsersToFavorite() {
		List<User> userList = userBo.getUserFavorite();

		return userList;

	}

	/**
	 * �ъ�⑹���� 紐⑤�� 移�援� ��蹂� 遺��ъ�ㅺ린
	 * 
	 * @param user_no
	 * @return
	 */
	@RequestMapping("/friends/{user_no:[0-9]+}")
	@ResponseBody
	public Object selectFriend(@PathVariable long user_no) {
		List<Friend> friendList = friendBo.getByUser(user_no);

		return friendList;
	}

	/**
	 * ���� ��蹂� ���곗�댄��
	 * 
	 * @param userNo
	 * @return
	 */

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateuser(@ModelAttribute User user) {
		userBo.update(user);

	}

	@RequestMapping("/upload/image/{userNo:[0-9]+}")
	@ResponseBody
	public Object UploadImage(@PathVariable long userNo, HttpServletRequest request)
			throws IllegalStateException, IOException {
		final String filePath = "C:/dev/user/";
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		File file = new File(filePath);
		if (file.exists() == false) {
			file.mkdirs(); // 吏�������移��� �대��媛� ���쇰㈃ �대������
		}
		while (iterator.hasNext()) {
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());

			if (multipartFile.isEmpty() == false) {
				System.out.println(multipartFile.getName());
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = userNo + ".png"; // ���ㅽ���쇱�대�+���μ�� (��踰���
													// ���λ�� �대�)

				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file); // ���쇱����

			}
		}

		return storedFileName;

	}

	@RequestMapping("/favorite/insert/{user_no:[0-9]+}/{company_no:[0-9]+}")
	@ResponseBody
	public void insertUserFavorite(@PathVariable long user_no, @PathVariable long company_no) {
		userFavoriteBo.insert(user_no, company_no);
	}

	@RequestMapping("/favorite/delete/{user_no:[0-9]+}/{company_no:[0-9]+}")
	@ResponseBody
	public void deleteUserFavorite(@PathVariable long user_no, @PathVariable long company_no) {
		userFavoriteBo.delete(user_no, company_no);
	}
	
	@RequestMapping("/question")
	@ResponseBody
	public Object selectUserQuestionRegisterDate() {
		return new SuccessResponse(userQuestionBo.getRegisterDateList());
	}
	
	
	

}