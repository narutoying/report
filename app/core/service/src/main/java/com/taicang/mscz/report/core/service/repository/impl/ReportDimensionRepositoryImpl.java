/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.taicang.mscz.report.common.dal.daointerface.ReportDimensionDAO;
import com.taicang.mscz.report.common.dal.daointerface.ReportDimensionValueDAO;
import com.taicang.mscz.report.common.dal.dataobject.ReportDimensionDO;
import com.taicang.mscz.report.common.dal.dataobject.ReportDimensionValueDO;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.ReportDimensionValue;
import com.taicang.mscz.report.core.service.repository.ReportDimensionRepository;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportDimensionRepositoryImpl.java, v 0.1 2013-7-3 上午9:31:54
 *          narutoying09@gmail.com Exp $
 */
public class ReportDimensionRepositoryImpl implements ReportDimensionRepository {

	private ReportDimensionDAO reportDimensionDAO;
	private ReportDimensionValueDAO reportDimensionValueDAO;

	/**
	 * @see com.taicang.mscz.report.core.service.repository.ReportDimensionRepository#createReportDimension(int,
	 *      java.util.List)
	 */
	@Override
	public void createReportDimension(int reportId,
			List<ReportDimension> conditionsWithValues) {
		for (ReportDimension dimension : conditionsWithValues) {
			createOneDimension(reportId, dimension);
		}
	}

	private void createOneDimension(int reportId, ReportDimension dimension) {
		dimension.setReportId(reportId);
		// 1. 新增维度
		int dimId = reportDimensionDAO.insert(convertToDO(dimension));
		// 2. 新增维度值
		List<ReportDimensionValue> values = dimension.getValues();
		for (ReportDimensionValue value : values) {
			value.setDimId(dimId);
			reportDimensionValueDAO.insert(convertToDO(value));
		}
	}

	private ReportDimensionDO convertToDO(ReportDimension dimension) {
		if (dimension == null) {
			return null;
		}
		ReportDimensionDO result = new ReportDimensionDO();
		BeanUtils.copyProperties(dimension, result);
		return result;
	}

	private ReportDimensionValueDO convertToDO(ReportDimensionValue value) {
		if (value == null) {
			return null;
		}
		ReportDimensionValueDO result = new ReportDimensionValueDO();
		result.setId(value.getId());
		result.setDimValue(value.getValue());
		result.setReportDimId(value.getDimId());
		return result;
	}

	public ReportDimensionDAO getReportDimensionDAO() {
		return reportDimensionDAO;
	}

	public void setReportDimensionDAO(ReportDimensionDAO reportDimensionDAO) {
		this.reportDimensionDAO = reportDimensionDAO;
	}

	public ReportDimensionValueDAO getReportDimensionValueDAO() {
		return reportDimensionValueDAO;
	}

	public void setReportDimensionValueDAO(
			ReportDimensionValueDAO reportDimensionValueDAO) {
		this.reportDimensionValueDAO = reportDimensionValueDAO;
	}

	@Override
	public List<ReportDimension> getReportDimensions(int reportId) {
		List<ReportDimension> result = new ArrayList<ReportDimension>();
		List<ReportDimensionDO> dimDOs = reportDimensionDAO
				.getByReportId(reportId);
		if (!CollectionUtils.isEmpty(dimDOs)) {
			for (ReportDimensionDO dimDO : dimDOs) {
				ReportDimension dim = new ReportDimension();
				dim.setId(dimDO.getId());
				dim.setName(dimDO.getName());
				dim.setReportId(reportId);
				dim.setValues(buildDimValues(dim.getId()));
				result.add(dim);
			}
		}
		return result;
	}

	private List<ReportDimensionValue> buildDimValues(int dimId) {
		List<ReportDimensionValue> result = new ArrayList<ReportDimensionValue>();
		List<ReportDimensionValueDO> valueDOs = reportDimensionValueDAO
				.getByDimId(dimId);
		if (!CollectionUtils.isEmpty(valueDOs)) {
			for (ReportDimensionValueDO value : valueDOs) {
				ReportDimensionValue v = new ReportDimensionValue();
				v.setDimId(dimId);
				v.setId(value.getId());
				v.setValue(value.getDimValue());
				result.add(v);
			}
		}
		return result;
	}

}
