/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.core.service.repository.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.taicang.mscz.report.common.dal.daointerface.UnitDAO;
import com.taicang.mscz.report.common.dal.daointerface.UnitExtraDAO;
import com.taicang.mscz.report.common.dal.dataobject.UnitDO;
import com.taicang.mscz.report.common.util.StringUtil;
import com.taicang.mscz.report.core.model.ReportConstant;
import com.taicang.mscz.report.core.model.ReportFieldMapping;
import com.taicang.mscz.report.core.model.Unit;
import com.taicang.mscz.report.core.service.exception.CommonException;
import com.taicang.mscz.report.core.service.exception.ResultCodeEnum;
import com.taicang.mscz.report.core.service.repository.UnitRepository;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: UnitRepositoryImpl.java, v 0.1 2013-7-3 上午9:32:11
 *          narutoying09@gmail.com Exp $
 */
public class UnitRepositoryImpl implements UnitRepository {

	private UnitDAO unitDAO;
	private UnitExtraDAO unitExtraDAO;

	/**
	 * @see com.taicang.mscz.report.core.service.repository.UnitRepository#createUnits(int,
	 *      java.util.List,
	 *      com.taicang.mscz.report.core.model.ReportFieldMapping)
	 */
	@Override
	public void createUnits(int reportId, List<Map<String, String>> unitDatas,
			ReportFieldMapping fieldMapping) {
		Map<String, String> bizToDbMapping = fieldMapping.getBizToDbMapping();
		for (Map<String, String> unitData : unitDatas) {
			UnitDO unitDO = new UnitDO();
			unitDO.setReportId(reportId);
			for (Map.Entry<String, String> entry : unitData.entrySet()) {
				setUnitField(unitDO, bizToDbMapping.get(entry.getKey()),
						entry.getValue());
			}
			unitDAO.insert(unitDO);
		}
	}

	/**
	 * 根据db字段名设置对应的值
	 * 
	 * @param unitDO
	 * @param dbFieldName
	 * @param value
	 */
	private void setUnitField(UnitDO unitDO, String dbFieldName, String value) {
		try {
			Method m = UnitDO.class.getDeclaredMethod("set" + dbFieldName,
					String.class);
			m.setAccessible(true);
			if (StringUtil.isBlank(value)) {
				value = ReportConstant.NULL;
			}
			m.invoke(unitDO, value);
		} catch (Exception e) {
			throw new CommonException(ResultCodeEnum.REFLECT_ERROR, e);
		}
	}

	public UnitDAO getUnitDAO() {
		return unitDAO;
	}

	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}

	@Override
	public List<Unit> getUnits(int reportId,
			Map<String, List<String>> dimensionsValue,
			ReportFieldMapping fieldMapping) {
		return batchConvertToDomain(unitExtraDAO.getByCondition(buildSql(
				reportId, dimensionsValue, fieldMapping)), reportId,
				fieldMapping);
	}

	private String buildSql(int reportId,
			Map<String, List<String>> dimensionsValue,
			ReportFieldMapping fieldMapping) {
		String sql = "select * from unit where report_id=" + reportId;
		if (dimensionsValue != null && dimensionsValue.entrySet().size() > 0) {
			for (Map.Entry<String, List<String>> entry : dimensionsValue
					.entrySet()) {
				Map<String, String> bizToDbMapping = fieldMapping
						.getBizToDbMapping();
				String biz = entry.getKey();
				String db = bizToDbMapping.get(biz);
				List<String> value = entry.getValue();
				if (!CollectionUtils.isEmpty(value)) {
					sql += " and " + buildInClause(db, value);
				}
			}
		}
		return sql;
	}

	/**
	 * 
	 * @param db
	 * @param value
	 * @return 返回值形如 F1 in ("aa","bb")
	 */
	private String buildInClause(String db, List<String> value) {
		StringBuilder sb = new StringBuilder();
		sb.append(db + " in (");
		for (String item : value) {
			sb.append("\"" + item + "\",");
		}
		String result = sb.substring(0, sb.length() - 1);
		result += ")";
		return result;
	}

	private List<Unit> batchConvertToDomain(List<UnitDO> dos, int reportId,
			ReportFieldMapping fieldMapping) {
		if (!CollectionUtils.isEmpty(dos)) {
			Map<String, String> dbToBizMapping = fieldMapping
					.getDbToBizMapping();
			List<Unit> result = new ArrayList<Unit>();
			for (UnitDO item : dos) {
				Unit unit = new Unit();
				unit.setId(item.getId());
				unit.setReportId(reportId);
				Map<String, String> bizData = new HashMap<String, String>();
				for (Map.Entry<String, String> entry : dbToBizMapping
						.entrySet()) {
					String db = entry.getKey();
					bizData.put(entry.getValue(), getUnitField(item, db));
				}
				unit.setBizData(bizData);
				result.add(unit);
			}
			return result;
		}
		return null;
	}

	/**
	 * 根据db字段名获取相应的值
	 * 
	 * @param unitDO
	 * @param dbFieldName
	 * @param value
	 */
	private String getUnitField(UnitDO unitDO, String dbFieldName) {
		try {
			Method m = UnitDO.class.getDeclaredMethod("get" + dbFieldName);
			m.setAccessible(true);
			return (String) m.invoke(unitDO);
		} catch (Exception e) {
			throw new CommonException(ResultCodeEnum.REFLECT_ERROR, e);
		}
	}

	public UnitExtraDAO getUnitExtraDAO() {
		return unitExtraDAO;
	}

	public void setUnitExtraDAO(UnitExtraDAO unitExtraDAO) {
		this.unitExtraDAO = unitExtraDAO;
	}
}
