package com.pd.exception;

public class UserIdIsNullException 
extends StoreException {
	
	@Override
	public String getMessage() {
		return "userId is Null";
	}

}
