package com.pd.pojo;
import java.util.*;
//���ﳵ�е�һ����Ʒ

import com.pd.pojo.paramData.Params;
public class ItemVO {
	private PdCartItem pdCartItem;
	//title,price
	private PdItem pdItem;
	List<Params> paramsList;

	public List<Params> getParamsList() {
		return paramsList;
	}
	public void setParamsList(List<Params> paramsList) {
		this.paramsList = paramsList;
	}
	public PdCartItem getPdCartItem() {
		return pdCartItem;
	}
	public void setPdCartItem(PdCartItem pdCartItem) {
		this.pdCartItem = pdCartItem;
	}
	public PdItem getPdItem() {
		return pdItem;
	}
	public void setPdItem(PdItem pdItem) {
		this.pdItem = pdItem;
	}
	
	
}
