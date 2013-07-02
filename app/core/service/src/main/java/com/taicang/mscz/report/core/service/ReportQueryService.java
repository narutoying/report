/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service;

import java.util.List;

import com.taicang.mscz.report.core.model.ReportQueryCondition;

/**
 * 报表查询服务
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportQueryService.java, v 0.1 2013-7-2 下午4:55:12
 *          narutoying09@gmail.com Exp $
 */
public interface ReportQueryService {
	/**
	 * 查询指定报表的查询维度、条件
	 * 
	 * @param reportId 报表id
	 * @return
	 */
	List<ReportQueryCondition> queryReportQueryConditions(int reportId);
	
	
}
