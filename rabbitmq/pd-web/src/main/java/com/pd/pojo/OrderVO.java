package com.pd.pojo;

import java.util.List;

public class OrderVO {
	PdOrder pdOrder;
	
	List<PdOrderItem> pdOrderItems;

	@Override
	public String toString() {
		return "OrderVO [pdOrder=" + pdOrder + ", pdOrderItems=" + pdOrderItems + "]";
	}

	public PdOrder getPdOrder() {
		return pdOrder;
	}

	public void setPdOrder(PdOrder pdOrder) {
		this.pdOrder = pdOrder;
	}

	public List<PdOrderItem> getPdOrderItems() {
		return pdOrderItems;
	}

	public void setPdOrderItems(List<PdOrderItem> pdOrderItems) {
		this.pdOrderItems = pdOrderItems;
	}

	

}
