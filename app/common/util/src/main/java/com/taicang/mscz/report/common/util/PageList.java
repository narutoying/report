package com.taicang.mscz.report.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.taicang.mscz.report.dal.util.Paginator;

public class PageList<T> implements Serializable {

	/**  */
	private static final long serialVersionUID = -4108031546466332132L;

	/**
	 * 数据信息列表
	 */
	private List<T> dataList;

	/**
	 * 分页器
	 */
	private Paginator paginator;

	/**
	 * 创建一个<code>PageList</code>。
	 */
	public PageList() {
		dataList = new ArrayList<T>();
		paginator = new Paginator();
	}

	/**
	 * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
	 * 
	 * @param c
	 *            要复制的<code>Collection</code>
	 */
	public PageList(Collection<T> c) {
		this(c, null);
	}

	/**
	 * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
	 * 
	 * @param c
	 *            要复制的<code>Collection</code>
	 */
	public PageList(Collection<T> c, Paginator paginator) {
		this.dataList = new ArrayList<T>(c);
		this.paginator = (paginator == null) ? new Paginator() : paginator;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

}
