package com.pd.pojo.paramData;

import java.util.List;

public class PdItemParamData {
	private String group;

	private List<Params> params;

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroup() {
		return this.group;
	}

	public void setParams(List<Params> params) {
		this.params = params;
	}

	public List<Params> getParams() {
		return this.params;
	}
}
