package com.taicang.mscz.report.common.util;

import com.taicang.mscz.report.dal.util.Paginator;

/**
 * 分页工具类
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PageUtil.java, v 0.1 2013-3-9 下午2:35:54 narutoying09@gmail.com Exp $
 */
public class PageUtil {
    /**
     * 每页显示数量
     */
    public static final int PAGE_SIZE = 20;

    public static final int MAX_SIZE  = 999;

    public static int getOffset(int pageSize, int pageNum) {
        int realPageNum = (pageNum < 1) ? 1 : pageNum;
        return (realPageNum - 1) * pageSize;
    }

    /**
     * Paginator转换
     * 
     * @param paginator
     *              待转换的Paginator对象
     * @return      
     */
    public static Paginator convertPaginator(Paginator paginator) {
        Paginator myPaginator = new Paginator();
        myPaginator.setItems(paginator.getItems());
        myPaginator.setItemsPerPage(paginator.getItemsPerPage());
        myPaginator.setPage(paginator.getPage());
        return myPaginator;
    }
}
