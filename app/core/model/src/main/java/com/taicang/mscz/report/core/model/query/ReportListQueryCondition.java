/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.model.query;

import com.taicang.mscz.report.common.util.PageUtil;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportListQueryCondition.java, v 0.1 2013-7-4 上午10:58:10
 *          narutoying09@gmail.com Exp $
 */
public class ReportListQueryCondition {
	private Integer pageSize = PageUtil.PAGE_SIZE;
	private Integer currentPage = 1; // 默认第一页
	private String reportName; // 模糊匹配报表名
	private String submitter; // 提交者

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
}
