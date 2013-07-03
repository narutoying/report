/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository;

import java.util.List;

import com.taicang.mscz.report.core.model.ReportFieldMapping;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportFieldMappingRepository.java, v 0.1 2013-7-3 上午9:18:21
 *          narutoying09@gmail.com Exp $
 */
public interface ReportFieldMappingRepository {
	/**
	 * 创建报表各维度字段映射关系
	 * 
	 * @param reportId
	 * @param bizNames
	 * @return
	 */
	void createFieldMapping(int reportId, List<String> bizNames);

	/**
	 * 获取指定报表的各字段映射关系
	 * 
	 * @param reportId
	 * @return
	 */
	ReportFieldMapping getByReportId(int reportId);
}
