/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.web.report;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taicang.mscz.report.biz.ReportManageComponent;
import com.taicang.mscz.report.biz.result.ReportResult;
import com.taicang.mscz.report.common.util.PageList;
import com.taicang.mscz.report.common.util.StringUtil;
import com.taicang.mscz.report.core.model.Report;
import com.taicang.mscz.report.core.model.ReportDimension;
import com.taicang.mscz.report.core.model.Unit;
import com.taicang.mscz.report.core.model.query.ReportListQueryCondition;
import com.taicang.mscz.report.core.model.query.ReportQueryCondition;
import com.taicang.mscz.report.core.service.ReportQueryService;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportController.java, v 0.1 2013-7-4 上午10:31:11
 *          narutoying09@gmail.com Exp $
 */
@Controller
public class ReportController {

	private static final String VM_PREFIX = "/report/";

	@Autowired
	private ReportManageComponent reportManageComponent;

	@Autowired
	private ReportQueryService reportQueryService;

	@RequestMapping("/report/addReport.htm")
	public String goAddReport() {
		return VM_PREFIX + "add.vm";
	}

	@RequestMapping(value = "/report/doAddReport.htm", method = RequestMethod.POST)
	public String doAddReport(
			@RequestParam(value = "file", required = false) MultipartFile file,
			Report report, ModelMap modelMap) {
		InputStream inputStream;
		try {
			inputStream = file.getInputStream();
			ReportResult result = reportManageComponent.receiveReport(report,
					inputStream);
			if (result.isSuccess()) {
				modelMap.addAttribute("reportId", result.getReportId());
				return "redirect:/report/showOneReport.htm";
			} else {
				modelMap.addAttribute("error", result.getResultMessage());
				return "error.vm";
			}
		} catch (IOException e) {
			modelMap.addAttribute("error", e.getMessage());
			return "error.vm";
		}
	}

	/**
	 * 
	 * @param reportId
	 *            报表id
	 * @param dimAndValuePairs
	 *            维度列与值对，形如 {机构^工行,机构^中行,机构^农行,机构^建行}
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/report/showOneReport.htm", method = RequestMethod.POST)
	public String showOneReport(int reportId, String[] dimAndValuePairs,
			ModelMap modelMap) {
		fillOneReportModelMap(reportId, dimAndValuePairs, modelMap);
		return VM_PREFIX + "one.vm";
	}

	@RequestMapping(value = "/report/showOneReport.htm", method = RequestMethod.GET)
	public String showOneReport(int reportId, ModelMap modelMap) {
		fillOneReportModelMap(reportId, null, modelMap);
		return VM_PREFIX + "one.vm";
	}

	private void fillOneReportModelMap(int reportId, String[] dimAndValuePairs,
			ModelMap modelMap) {
		modelMap.addAttribute("report",
				reportQueryService.queryReport(reportId));
		List<ReportDimension> reportDims = reportQueryService
				.queryReportDimensions(reportId);
		List<Unit> reportUnits = reportQueryService.queryReportUnits(reportId,
				buildQueryConditions(dimAndValuePairs));
		modelMap.addAttribute("reportDims", reportDims);
		modelMap.addAttribute("reportContent",
				buildContent(reportDims, reportUnits));
		modelMap.addAttribute("selectedDimValuesMap",
				buildSelectedDimValuesMap(dimAndValuePairs));
	}

	private Map<String, String> buildSelectedDimValuesMap(
			String[] dimAndValuePairs) {
		Map<String, String> result = new HashMap<String, String>();
		if (!ArrayUtils.isEmpty(dimAndValuePairs)) {
			for (String pair : dimAndValuePairs) {
				result.put(pair, "true");
			}
		}
		return result;
	}

	private ReportQueryCondition buildQueryConditions(String[] dimAndValuePairs) {
		ReportQueryCondition result = new ReportQueryCondition();
		Map<String, List<String>> dimensionsValue = result.getDimensionsValue();
		result.setDimensionsValue(dimensionsValue);
		if (!ArrayUtils.isEmpty(dimAndValuePairs)) {
			for (String pair : dimAndValuePairs) {
				String[] split = StringUtil.split(pair, "^");
				if (split != null && split.length == 2) {
					String dimName = split[0];
					String dimValue = split[1];
					List<String> list = dimensionsValue.get(dimName);
					if (!CollectionUtils.isEmpty(list)) {
						list.add(dimValue);
					} else {
						List<String> newList = new ArrayList<String>();
						newList.add(dimValue);
						dimensionsValue.put(dimName, newList);
					}
				}
			}
		}
		return result;
	}

	private List<List<String>> buildContent(List<ReportDimension> dims,
			List<Unit> units) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (!CollectionUtils.isEmpty(units) && !CollectionUtils.isEmpty(dims)) {
			for (Unit unit : units) {
				Map<String, String> bizData = unit.getBizData();
				List<String> oneLine = new ArrayList<String>();
				for (ReportDimension dim : dims) {
					String dimName = dim.getName();
					oneLine.add(bizData.get(dimName));
				}
				result.add(oneLine);
			}
		}
		return result;
	}

	@RequestMapping("/report/showReportList.htm")
	public String showReportList(ReportListQueryCondition queryCondition,
			ModelMap modelMap) {
		PageList<Report> pageList = reportQueryService
				.queryReportsByCondition(queryCondition);
		modelMap.addAttribute("pageList", pageList);
		return VM_PREFIX + "list.vm";
	}
}
