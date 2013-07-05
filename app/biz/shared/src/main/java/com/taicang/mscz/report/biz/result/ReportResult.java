/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.biz.result;

import com.taicang.mscz.report.facade.model.CommonResult;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportResult.java, v 0.1 2013-7-4 上午11:04:34
 *          narutoying09@gmail.com Exp $
 */
public class ReportResult extends CommonResult {
	/**  */
	private static final long serialVersionUID = 2775183969784882836L;
	private int reportId;

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
}
