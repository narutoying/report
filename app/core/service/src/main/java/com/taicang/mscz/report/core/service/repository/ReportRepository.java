/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository;

import com.taicang.mscz.report.core.model.Report;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportRepository.java, v 0.1 2013-7-3 上午8:51:43
 *          narutoying09@gmail.com Exp $
 */
public interface ReportRepository {
	Report getReport(int reportId);

	Report getReport(String reportName);

	/**
	 * 创建一个报表
	 * 
	 * @param report
	 * @return 报表在db中的id
	 */
	int createReport(Report report);
}
