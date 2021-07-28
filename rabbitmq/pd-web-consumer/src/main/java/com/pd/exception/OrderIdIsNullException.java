package com.pd.exception;

public class OrderIdIsNullException 
extends StoreException {
	
	@Override
	public String getMessage() {
		return "orderId is Null";
	}

}
