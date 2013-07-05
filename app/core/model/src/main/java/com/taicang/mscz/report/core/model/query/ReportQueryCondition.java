/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.model.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报表查询条件
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportQueryCondition.java, v 0.1 2013-7-3 ����8:33:05
 *          narutoying09@gmail.com Exp $
 */
public class ReportQueryCondition {
	private Map<String/* 维度名 */, List<String>/* 维度值列表 */> dimensionsValue = new HashMap<String, List<String>>();

	public Map<String, List<String>> getDimensionsValue() {
		return dimensionsValue;
	}

	public void setDimensionsValue(Map<String, List<String>> dimensionsValue) {
		this.dimensionsValue = dimensionsValue;
	}

}
