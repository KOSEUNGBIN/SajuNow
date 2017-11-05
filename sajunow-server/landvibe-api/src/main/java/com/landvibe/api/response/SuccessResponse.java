package com.landvibe.api.response;

import com.landvibe.common.model.BaseModel;

public class SuccessResponse extends BaseModel implements CommonResponse {
	private static final int API_SUCCESS_CODE = 0;

	private static final long serialVersionUID = 6839461196719097278L;

	private final Object result;

	public SuccessResponse(Object result) {
		this.result = result;
	}

	@Override
	public int getCode() {
		return API_SUCCESS_CODE;
	}

	@Override
	public Object getResult() {
		return result;
	}

}
