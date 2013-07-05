/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.biz.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.taicang.mscz.report.biz.ReportManageComponent;
import com.taicang.mscz.report.biz.result.ReportResult;
import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportConstant;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.ReportDimensionValue;
import com.taicang.mscz.report.core.service.ReportManageService;
import com.taicang.mscz.report.core.service.exception.CommonException;
import com.taicang.mscz.report.core.service.util.template.CommonManageTemplate;
import com.taicang.mscz.report.core.service.util.template.callback.CommonManageCallback;
import com.taicang.mscz.report.facade.model.CommonResult;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportManageComponentImpl.java, v 0.1 2013-7-3 下午4:47:20
 *          narutoying09@gmail.com Exp $
 */
public class ReportManageComponentImpl implements ReportManageComponent {

	private Logger logger = LoggerFactory
			.getLogger(ReportManageComponentImpl.class);

	private CommonManageTemplate commonManageTemplate;

	private ReportManageService reportManageService;

	/**
	 * @see com.taicang.mscz.report.biz.ReportManageComponent#receiveReport(com.taicang.mscz.report.core.model.Report,
	 *      java.io.InputStream)
	 */
	@Override
	public ReportResult receiveReport(final Report report,
			final InputStream inputStream) {
		final ReportResult result = new ReportResult();
		try {
			commonManageTemplate.manageWithTransaction(result,
					new CommonManageCallback() {

						@Override
						public void doManage() {
							List<ReportDimension> conditionsWithValues = new ArrayList<ReportDimension>();
							List<Map<String, String>> unitDatas = new ArrayList<Map<String, String>>();
							List<String> tmpDimNames = new ArrayList<String>();
							Map<String, Set<String>> tmpDimValues = new HashMap<String, Set<String>>();
							/*
							 * 1. 构造维度列与值 1.1 excel第一行为标题，每一列作为一个维度 1.2
							 * 维度值将在2中遍历时进行填充（注意去重） 2. 构造单元数据，遍历除标题外所有行进行填充
							 */
							HSSFWorkbook hssfWorkbook;
							try {
								hssfWorkbook = new HSSFWorkbook(inputStream);
								// 先只处理1个Sheet
								HSSFSheet hssfSheet = hssfWorkbook
										.getSheetAt(0);
								HSSFRow titleRow = hssfSheet.getRow(0);
								int lastCellNum = titleRow.getLastCellNum();
								for (int colNum = 0; colNum < lastCellNum; colNum++) {
									String value = getValue(
											titleRow.getCell(colNum), 0, colNum);
									tmpDimNames.add(value);
									ReportDimension dimension = new ReportDimension();
									dimension.setName(value);
									conditionsWithValues.add(dimension);
								}
								// 循环行Row
								for (int rowNum = 1; rowNum <= hssfSheet
										.getLastRowNum(); rowNum++) {
									HSSFRow hssfRow = hssfSheet.getRow(rowNum);
									Map<String, String> unitData = new HashMap<String, String>();
									unitDatas.add(unitData);
									if (hssfRow == null) {
										continue;
									}
									for (int colNum = 0; colNum < lastCellNum; colNum++) {
										String value = getValue(
												hssfRow.getCell(colNum),
												rowNum, colNum);
										String dimName = tmpDimNames
												.get(colNum);
										unitData.put(dimName, value);
										buildDimValues(tmpDimValues, dimName,
												value);
									}
								}
								fillDimValues(conditionsWithValues,
										tmpDimValues);
								int reportId = reportManageService
										.receiveReport(report,
												conditionsWithValues, unitDatas);
								result.setReportId(reportId);
								CommonResult.buildResult(result, true,
										"接收并处理报表文件成功");
							} catch (IOException e) {
								logger.error("", e);
							}
						}

						@Override
						public void checkParameter() {

						}
					});
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("关闭输入流出错" + "", e);
			}
		}
		return result;
	}

	private void fillDimValues(List<ReportDimension> conditionsWithValues,
			Map<String, Set<String>> tmpDimValues) {
		for (ReportDimension dimension : conditionsWithValues) {
			String name = dimension.getName();
			Set<String> set = tmpDimValues.get(name);
			List<ReportDimensionValue> values = new ArrayList<ReportDimensionValue>();
			for (String dimValue : set) {
				values.add(new ReportDimensionValue(dimValue));
			}
			dimension.setValues(values);
		}
	}

	/**
	 * 填充维度列值映射表
	 * 
	 * @param tmpDimValues
	 * @param dimName
	 * @param value
	 */
	private void buildDimValues(Map<String, Set<String>> tmpDimValues,
			String dimName, String value) {
		if (tmpDimValues.containsKey(dimName)) {
			tmpDimValues.get(dimName).add(value);
		} else {
			Set<String> set = new TreeSet<String>();
			set.add(value);
			tmpDimValues.put(dimName, set);
		}
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell, int rowNum, int colNum) {
		try {
			if (hssfCell != null) {
				hssfCell.setCellType(hssfCell.CELL_TYPE_STRING);
				return hssfCell.getStringCellValue();
			} else {
				return ReportConstant.NULL;
			}
		} catch (Exception e) {
			throw new CommonException(
					"获取指定列值出错[" + rowNum + "," + colNum + "]", e);
		}
		/*
		 * if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) { //
		 * 返回布尔类型的值 return String.valueOf(hssfCell.getBooleanCellValue()); }
		 * else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) { //
		 * 返回数值类型的值 return String.valueOf(hssfCell.getNumericCellValue()); }
		 * else { // 返回字符串类型的值 return
		 * String.valueOf(hssfCell.getStringCellValue()); }
		 */
	}

	public CommonManageTemplate getCommonManageTemplate() {
		return commonManageTemplate;
	}

	public void setCommonManageTemplate(
			CommonManageTemplate commonManageTemplate) {
		this.commonManageTemplate = commonManageTemplate;
	}

	public ReportManageService getReportManageService() {
		return reportManageService;
	}

	public void setReportManageService(ReportManageService reportManageService) {
		this.reportManageService = reportManageService;
	}

}
