/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.ReportFieldMapping;
import com.taicang.mscz.report.core.service.ReportManageService;
import com.taicang.mscz.report.core.service.exception.CommonException;
import com.taicang.mscz.report.core.service.repository.ReportDimensionRepository;
import com.taicang.mscz.report.core.service.repository.ReportFieldMappingRepository;
import com.taicang.mscz.report.core.service.repository.ReportRepository;
import com.taicang.mscz.report.core.service.repository.UnitRepository;
import com.taicang.mscz.report.core.service.util.ParaCheckUtil;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportManageServiceImpl.java, v 0.1 2013-7-3 ����8:37:47
 *          narutoying09@gmail.com Exp $
 */
public class ReportManageServiceImpl implements ReportManageService {

	private ReportRepository reportRepository;
	private ReportDimensionRepository reportDimensionRepository;
	private ReportFieldMappingRepository reportFieldMappingRepository;
	private UnitRepository unitRepository;

	@Override
	public int receiveReport(Report report,
			List<ReportDimension> conditionsWithValues,
			List<Map<String, String>> unitDatas) {
		// 1
		check(report, conditionsWithValues, unitDatas);
		// 2
		init(report, conditionsWithValues, unitDatas);
		return report.getId();
	}

	private void init(Report report,
			List<ReportDimension> conditionsWithValues,
			List<Map<String, String>> unitDatas) {
		int reportId = reportRepository.createReport(report);
		reportDimensionRepository.createReportDimension(reportId,
				conditionsWithValues);
		reportFieldMappingRepository.createFieldMapping(reportId,
				buildBizNames(conditionsWithValues));
		ReportFieldMapping fieldMapping = reportFieldMappingRepository
				.getByReportId(reportId);
		unitRepository.createUnits(reportId, unitDatas, fieldMapping);
		report.setId(reportId);
	}

	private List<String> buildBizNames(
			List<ReportDimension> conditionsWithValues) {
		List<String> result = new ArrayList<String>();
		for (ReportDimension dimension : conditionsWithValues) {
			result.add(dimension.getName());
		}
		return result;
	}

	private void check(Report report,
			List<ReportDimension> conditionsWithValues,
			List<Map<String, String>> unitDatas) {
		ParaCheckUtil.checkParaNotNull(report);
		ParaCheckUtil.checkParaNotEmpty(conditionsWithValues);
		ParaCheckUtil.checkParaNotEmpty(unitDatas);
		String reportName = report.getName();
		Report report2 = reportRepository.getReport(reportName);
		if (report2 != null) {
			throw new CommonException("已存在报表【" + reportName + "】请更换报表名");
		}
	}

	public ReportRepository getReportRepository() {
		return reportRepository;
	}

	public void setReportRepository(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
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

}
