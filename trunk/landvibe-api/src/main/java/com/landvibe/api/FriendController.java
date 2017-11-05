package com.landvibe.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.landvibe.api.response.ErrorResponse;
import com.landvibe.api.response.SuccessResponse;
import com.landvibe.core.friend.Friend;
import com.landvibe.core.friend.FriendBo;

/**
 * 친구 api
 * 
 * 사용 안할듯 논의 후 삭제 예정
 * 
 * @author 건희
 *
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private FriendBo friendBo;

	/**
	 * 
	 * 친구 테이블 전체 불러오기
	 */
	@RequestMapping("/all")
	@ResponseBody
	public Object allFriend() {

		List<Friend> friendList = friendBo.getAll();
		if (friendList == null) {
			return new ErrorResponse("사용자의 친구정보가없습니다.");
		} else {
			return new SuccessResponse(friendList);
		}

	}

	/**
	 * 
	 * user_no의 전체 친구 불러오기
	 */
	@RequestMapping("/{userNo:[0-9]+}")
	@ResponseBody
	public Object selectFriend(@PathVariable long userNo) {
		List<Friend> friend = friendBo.getByUser(userNo);

		return friend;

	}

	/**
	 * 
	 * user_no의 친구 한명 불러오기
	 */
	@RequestMapping("/one/{friendNo:[0-9]+}")
	@ResponseBody
	public Object selectOneFriend(@PathVariable long friendNo) {
		Friend friend = friendBo.getByNo(friendNo);

		return friend;

	}

	/**
	 * 
	 * 친구 추가
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public void insertFriend(@ModelAttribute Friend friend) {

		friendBo.create(friend);

	}

	/**
	 * 
	 * 친구 데이터 수정
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void updateFriend(@ModelAttribute Friend friend) {

		friendBo.update(friend);

	}

	/**
	 * 
	 * 친구 데이터 삭제
	 */
	@RequestMapping("/delete/{friendNo:[0-9]+}")
	@ResponseBody
	public void deleteFriend(@PathVariable long friendNo) {
		friendBo.delete(friendNo);

	}

}
