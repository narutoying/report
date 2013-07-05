/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.biz;

import java.io.InputStream;

import com.taicang.mscz.report.biz.result.ReportResult;
import com.taicang.mscz.report.core.model.Report;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: ReportManageComponent.java, v 0.1 2013-7-3 下午4:38:56
 *          narutoying09@gmail.com Exp $
 */
public interface ReportManageComponent {
	/**
	 * 接受报表上传请求
	 * 
	 * @param report
	 *            报表
	 * @param inputStream
	 *            上传文件输入流
	 * @return
	 */
	ReportResult receiveReport(Report report, InputStream inputStream);

}
