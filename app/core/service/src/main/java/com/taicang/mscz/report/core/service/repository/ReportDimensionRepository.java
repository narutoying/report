/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository;

import java.util.List;

import com.taicang.mscz.report.core.model.ReportDimension;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportDimensionRepository.java, v 0.1 2013-7-3 上午9:01:40
 *          narutoying09@gmail.com Exp $
 */
public interface ReportDimensionRepository {
	/**
	 * 创建报表维度列与值
	 * 
	 * @param reportId
	 * @param conditionsWithValues
	 */
	void createReportDimension(int reportId,
			List<ReportDimension> conditionsWithValues);

	/**
	 * 获取指定报表的维度列与值
	 * 
	 * @param reportId
	 * @return
	 */
	List<ReportDimension> getReportDimensions(int reportId);
	

}
