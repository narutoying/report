/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.taicang.mscz.report.common.dal.daointerface.ReportDAO;
import com.taicang.mscz.report.common.dal.dataobject.ReportDO;
import com.taicang.mscz.report.common.util.PageList;
import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.service.repository.ReportRepository;
import com.taicang.mscz.report.dal.util.PageUtil;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportRepositoryImpl.java, v 0.1 2013-7-3 上午9:32:25
 *          narutoying09@gmail.com Exp $
 */
public class ReportRepositoryImpl implements ReportRepository {

	private ReportDAO reportDAO;

	/**
	 * @see com.taicang.mscz.report.core.service.repository.ReportRepository#getReport(java.lang.String)
	 */
	@Override
	public Report getReport(String reportName) {
		return convertToDomain(reportDAO.getByName(reportName));
	}

	private Report convertToDomain(ReportDO reportDO) {
		if (reportDO == null) {
			return null;
		}
		Report result = new Report();
		BeanUtils.copyProperties(reportDO, result);
		return result;
	}

	private ReportDO convertToDO(Report report) {
		if (report == null) {
			return null;
		}
		ReportDO result = new ReportDO();
		BeanUtils.copyProperties(report, result);
		return result;
	}

	/**
	 * @see com.taicang.mscz.report.core.service.repository.ReportRepository#createReport(com.taicang.mscz.report.core.model.Report)
	 */
	@Override
	public int createReport(Report report) {
		return reportDAO.insert(convertToDO(report));
	}

	public ReportDAO getReportDAO() {
		return reportDAO;
	}

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	@Override
	public Report getReport(int reportId) {
		return convertToDomain(reportDAO.getById(reportId));
	}

	@Override
	public PageList<Report> getReportPageList(String name, String submitter,
			Integer pageNum, Integer pageSize) {
		PageList<Report> result = new PageList<Report>();
		com.taicang.mscz.report.dal.util.PageList pageList = reportDAO
				.getByCondition(submitter, name, pageSize,
						PageUtil.getOffset(pageSize, pageNum));
		result.setPaginator(pageList.getPaginator());
		result.setDataList(convertToData(pageList));
		return result;
	}

	private List<Report> convertToData(
			com.taicang.mscz.report.dal.util.PageList pageList) {
		List<Report> result = new ArrayList<Report>();
		for (Object o : pageList) {
			if (o instanceof ReportDO) {
				result.add(convertToDomain((ReportDO) o));
			}
		}
		return result;
	}

}
