package com.landvibe.core.post;

import com.landvibe.common.model.BaseModel;

public class Post extends BaseModel {
	private static final long serialVersionUID = 2323573286424370398L;

	private long postNo;

	public long getPostNo() {
		return postNo;
	}

	public void setPostNo(long postNo) {
		this.postNo = postNo;
	}
}
