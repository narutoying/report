/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.impl;

import java.util.List;

import com.taicang.mscz.report.common.util.PageList;
import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.ReportFieldMapping;
import com.taicang.mscz.report.core.model.Unit;
import com.taicang.mscz.report.core.model.query.ReportListQueryCondition;
import com.taicang.mscz.report.core.model.query.ReportQueryCondition;
import com.taicang.mscz.report.core.service.ReportQueryService;
import com.taicang.mscz.report.core.service.repository.ReportDimensionRepository;
import com.taicang.mscz.report.core.service.repository.ReportFieldMappingRepository;
import com.taicang.mscz.report.core.service.repository.ReportRepository;
import com.taicang.mscz.report.core.service.repository.UnitRepository;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportQueryServiceImpl.java, v 0.1 2013-7-3 ����8:37:59
 *          narutoying09@gmail.com Exp $
 */
public class ReportQueryServiceImpl implements ReportQueryService {

	private ReportDimensionRepository reportDimensionRepository;
	private ReportFieldMappingRepository reportFieldMappingRepository;
	private UnitRepository unitRepository;
	private ReportRepository reportRepository;

	/**
	 * @see com.taicang.mscz.report.core.service.ReportQueryService#queryReportDimensions(int)
	 */
	@Override
	public List<ReportDimension> queryReportDimensions(int reportId) {
		return reportDimensionRepository.getReportDimensions(reportId);
	}

	/**
	 * @see com.taicang.mscz.report.core.service.ReportQueryService#queryReportUnits(int,
	 *      com.taicang.mscz.report.core.model.query.ReportQueryCondition)
	 */
	@Override
	public List<Unit> queryReportUnits(int reportId,
			ReportQueryCondition queryCondition) {
		ReportFieldMapping fieldMapping = reportFieldMappingRepository
				.getByReportId(reportId);
		return unitRepository.getUnits(reportId,
				queryCondition.getDimensionsValue(), fieldMapping);
	}

	public ReportDimensionRepository getReportDimensionRepository() {
		return reportDimensionRepository;
	}

	public void setReportDimensionRepository(
			ReportDimensionRepository reportDimensionRepository) {
		this.reportDimensionRepository = reportDimensionRepository;
	}

	public ReportFieldMappingRepository getReportFieldMappingRepository() {
		return reportFieldMappingRepository;
	}

	public void setReportFieldMappingRepository(
			ReportFieldMappingRepository reportFieldMappingRepository) {
		this.reportFieldMappingRepository = reportFieldMappingRepository;
	}

	public UnitRepository getUnitRepository() {
		return unitRepository;
	}

	public void setUnitRepository(UnitRepository unitRepository) {
		this.unitRepository = unitRepository;
	}

	@Override
	public Report queryReport(int reportId) {
		return reportRepository.getReport(reportId);
	}

	public ReportRepository getReportRepository() {
		return reportRepository;
	}

	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}

	@Override
	public PageList<Report> queryReportsByCondition(
			ReportListQueryCondition queryCondition) {
		return reportRepository.getReportPageList(
				queryCondition.getReportName(), queryCondition.getSubmitter(),
				queryCondition.getCurrentPage(), queryCondition.getPageSize());
	}

}
