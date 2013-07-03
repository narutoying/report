/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository;

import java.util.List;
import java.util.Map;

import com.taicang.mscz.report.core.model.ReportFieldMapping;
import com.taicang.mscz.report.core.model.Unit;

/**
 * 数据单元仓储
 * 
 * @author narutoying09@gmail.com
 * @version $Id: UnitRepository.java, v 0.1 2013-7-3 上午9:24:45
 *          narutoying09@gmail.com Exp $
 */
public interface UnitRepository {
	/**
	 * 创建数据单元
	 * 
	 * @param reportId
	 * @param unitDatas
	 * @param fieldMapping
	 */
	void createUnits(int reportId, List<Map<String, String>> unitDatas,
			ReportFieldMapping fieldMapping);

	List<Unit> getUnits(int reportId,
			Map<String/* 维度名 */, List<String>/* 维度值列表 */> dimensionsValue,
			ReportFieldMapping fieldMapping);

}
