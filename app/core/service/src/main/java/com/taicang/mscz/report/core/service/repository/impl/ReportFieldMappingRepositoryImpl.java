/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.taicang.mscz.report.common.dal.daointerface.ReportFieldMappingDAO;
import com.taicang.mscz.report.common.dal.dataobject.ReportFieldMappingDO;
import com.taicang.mscz.report.core.model.ReportFieldMapping;
import com.taicang.mscz.report.core.model.ReportUnitFiledConstant;
import com.taicang.mscz.report.core.service.exception.CommonException;
import com.taicang.mscz.report.core.service.repository.ReportFieldMappingRepository;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportFieldMappingRepositoryImpl.java, v 0.1 2013-7-3 上午9:32:41
 *          narutoying09@gmail.com Exp $
 */
public class ReportFieldMappingRepositoryImpl implements
		ReportFieldMappingRepository {

	private ReportFieldMappingDAO reportFieldMappingDAO;

	/**
	 * @see com.taicang.mscz.report.core.service.repository.ReportFieldMappingRepository#createFieldMapping(int,
	 *      java.util.List)
	 */
	@Override
	public void createFieldMapping(int reportId, List<String> bizNames) {
		checkBizNameSize(bizNames);
		int index = 1;
		for (String bizName : bizNames) {
			ReportFieldMappingDO reportFieldMappingDO = new ReportFieldMappingDO();
			reportFieldMappingDO.setReportId(reportId);
			reportFieldMappingDO.setBizField(bizName);
			reportFieldMappingDO.setDbField(ReportUnitFiledConstant
					.getDBField(index++));
			reportFieldMappingDAO.insert(reportFieldMappingDO);
		}
	}

	/**
	 * 检查维度列数量是否超过最大上限
	 * 
	 * @param bizNames
	 */
	private void checkBizNameSize(List<String> bizNames) {
		if (CollectionUtils.isEmpty(bizNames)) {
			throw new CommonException("维度列不能为空");
		}
		if (bizNames.size() > ReportUnitFiledConstant.MAX_SIZE) {
			throw new CommonException("维度列数量是否超过最大上限["
					+ ReportUnitFiledConstant.MAX_SIZE + "]");
		}
	}

	public ReportFieldMappingDAO getReportFieldMappingDAO() {
		return reportFieldMappingDAO;
	}

	public void setReportFieldMappingDAO(
			ReportFieldMappingDAO reportFieldMappingDAO) {
		this.reportFieldMappingDAO = reportFieldMappingDAO;
	}

	@Override
	public ReportFieldMapping getByReportId(int reportId) {
		ReportFieldMapping result = new ReportFieldMapping();
		result.setReportId(reportId);
		Map<String, String> bizToDbMapping = new HashMap<String, String>();
		result.setBizToDbMapping(bizToDbMapping);
		Map<String, String> dbToBizMapping = new HashMap<String, String>();
		result.setDbToBizMapping(dbToBizMapping);
		List<ReportFieldMappingDO> dos = reportFieldMappingDAO
				.getByReportId(reportId);
		if (!CollectionUtils.isEmpty(dos)) {
			for (ReportFieldMappingDO data : dos) {
				String bizField = data.getBizField();
				String dbField = data.getDbField();
				bizToDbMapping.put(bizField, dbField);
				dbToBizMapping.put(dbField, bizField);
			}
		}
		return result;
	}
}
