package com.landvibe.api.response;

import com.landvibe.common.model.BaseModel;

public class ErrorResponse extends BaseModel implements CommonResponse {
	private static final int API_ERROR_CODE = 1;

	private static final long serialVersionUID = -1500837430324880845L;

	private final Object result;

	public ErrorResponse(Object result) {
		this.result = result;
	}

	@Override
	public int getCode() {
		return -API_ERROR_CODE;
	}

	@Override
	public Object getResult() {
		return result;
	}

}
