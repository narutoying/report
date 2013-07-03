/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.test.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.ReportDimensionValue;
import com.taicang.mscz.report.core.service.ReportManageService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: TestContactService.java, v 0.1 2013-4-9 下午9:16:10
 *          narutoying09@gmail.com Exp $
 */
public class TestReportManageService extends BaseTestCase {

	@Autowired
	private ReportManageService reportManageService;

	@Test
	public void test() {
		Report report = buildReport();
		List<ReportDimension> conditionsWithValues = buildDimensions();
		List<Map<String, String>> unitDatas = buildUnitDatas();
		reportManageService.receiveReport(report, conditionsWithValues,
				unitDatas);
	}

	private List<Map<String, String>> buildUnitDatas() {
		List<Map<String, String>> unitDatas = new ArrayList<Map<String, String>>();
		Map<String, String> item1 = new HashMap<String, String>();
		item1.put("机构", "家乐福");
		item1.put("姓名", "小张");
		item1.put("存款", "1000.0");
		unitDatas.add(item1);
		Map<String, String> item2 = new HashMap<String, String>();
		item2.put("机构", "家乐福");
		item2.put("姓名", "小李");
		item2.put("存款", "1000.0");
		unitDatas.add(item2);
		Map<String, String> item3 = new HashMap<String, String>();
		item3.put("机构", "沃尔玛");
		item3.put("姓名", "小张");
		item3.put("存款", "2000.0");
		unitDatas.add(item3);
		Map<String, String> item4 = new HashMap<String, String>();
		item4.put("机构", "沃尔玛");
		item4.put("姓名", "小李");
		item4.put("存款", "2000.0");
		unitDatas.add(item4);
		return unitDatas;
	}

	private List<ReportDimension> buildDimensions() {
		List<ReportDimension> conditionsWithValues = new ArrayList<ReportDimension>();
		ReportDimension d1 = new ReportDimension();
		d1.setName("机构");
		List<ReportDimensionValue> values1 = new ArrayList<ReportDimensionValue>();
		values1.add(new ReportDimensionValue("家乐福"));
		values1.add(new ReportDimensionValue("沃尔玛"));
		d1.setValues(values1);
		ReportDimension d2 = new ReportDimension();
		d2.setName("姓名");
		List<ReportDimensionValue> values2 = new ArrayList<ReportDimensionValue>();
		values2.add(new ReportDimensionValue("小张"));
		values2.add(new ReportDimensionValue("小李"));
		d2.setValues(values2);
		ReportDimension d3 = new ReportDimension();
		d3.setName("存款");
		List<ReportDimensionValue> values3 = new ArrayList<ReportDimensionValue>();
		values3.add(new ReportDimensionValue("1000.0"));
		values3.add(new ReportDimensionValue("2000.0"));
		d3.setValues(values3);

		conditionsWithValues.add(d1);
		conditionsWithValues.add(d2);
		conditionsWithValues.add(d3);
		return conditionsWithValues;
	}

	private Report buildReport() {
		Report report = new Report();
		report.setName("test");
		report.setSubmitter("戴诚");
		return report;
	}

}
