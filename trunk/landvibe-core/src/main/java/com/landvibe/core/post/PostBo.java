package com.landvibe.core.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostBo {
	@Autowired
	private PostDao postDao;

	public Post get(long postNo) {
		return postDao.select(postNo);
	}
}