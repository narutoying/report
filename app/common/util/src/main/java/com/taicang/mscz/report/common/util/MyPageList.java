/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.taicang.mscz.report.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.taicang.mscz.report.dal.util.Paginator;

/**
 * 封装数据和分页器
 * 
 * @author narutoying09@gmail.com
 * @version $Id: MyPageList.java, v 0.1 2013-3-4 下午8:27:36 narutoying09@gmail.com Exp $
 */
public class MyPageList<T> implements Serializable {

    /**  */
    private static final long serialVersionUID = -4108031546466332132L;
    private List<T>           dataList;

    private Paginator         paginator;

    public MyPageList() {
        dataList = new ArrayList<T>();
        paginator = new Paginator();
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
