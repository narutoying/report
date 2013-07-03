package com.taicang.mscz.report.core.model;

import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReportFieldMapping {

	private int reportId;

	private Map<String, String> bizToDbMapping;

	private Map<String, String> dbToBizMapping;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public Map<String, String> getBizToDbMapping() {
		return bizToDbMapping;
	}

	public void setBizToDbMapping(Map<String, String> bizToDbMapping) {
		this.bizToDbMapping = bizToDbMapping;
	}

	public Map<String, String> getDbToBizMapping() {
		return dbToBizMapping;
	}

	public void setDbToBizMapping(Map<String, String> dbToBizMapping) {
		this.dbToBizMapping = dbToBizMapping;
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

}
