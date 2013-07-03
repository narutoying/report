/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.test.integration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.Unit;
import com.taicang.mscz.report.core.model.query.ReportQueryCondition;
import com.taicang.mscz.report.core.service.ReportQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: TestContactService.java, v 0.1 2013-4-9 下午9:16:10
 *          narutoying09@gmail.com Exp $
 */
public class TestReportQueryService extends BaseTestCase {

	@Autowired
	private ReportQueryService reportQueryService;

	int reportId = 5;

	@Test
	public void testQueryReportDimensions() {
		List<ReportDimension> queryReportDimensions = reportQueryService
				.queryReportDimensions(reportId);
		System.out.println(ToStringBuilder.reflectionToString(
				queryReportDimensions, ToStringStyle.SHORT_PREFIX_STYLE));
	}

	@Test
	public void testQueryReportUnits() {
		ReportQueryCondition queryCondition = new ReportQueryCondition();
		Map<String, List<String>> dimensionsValue = new HashMap<String, List<String>>();
		dimensionsValue.put("机构", Arrays.asList(new String[] { "家乐福" }));
		// dimensionsValue.put("姓名", Arrays.asList(new String[] { "小李" }));
		queryCondition.setDimensionsValue(dimensionsValue);
		List<Unit> reportUnits = reportQueryService.queryReportUnits(reportId,
				queryCondition);
		System.out.println(reportUnits.size());
	}
}
