/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.test.integration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.taicang.mscz.report.biz.ReportManageComponent;
import com.taicang.mscz.report.core.model.Report;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: TestContactService.java, v 0.1 2013-4-9 下午9:16:10
 *          narutoying09@gmail.com Exp $
 */
public class TestReportManageComponent extends BaseTestCase {

	@Autowired
	private ReportManageComponent reportManageComponent;;

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		Report report = new Report();
		report.setName("2012-2013贴现6月底台账");
		report.setSubmitter("cheng.dai");
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("2012-2013贴现6月底台账.xls");
			reportManageComponent.receiveReport(report, inputStream);
			long end = System.currentTimeMillis();
			System.out.println("导入共耗时" + (end - start) + "ms");
		} catch (FileNotFoundException e) {
			System.err.println(e);
		}
	}
}
