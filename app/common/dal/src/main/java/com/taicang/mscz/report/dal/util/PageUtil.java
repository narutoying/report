package com.taicang.mscz.report.dal.util;

public class PageUtil {

    public static int getOffset(int pageSize, int pageNum) {
        int realPageNum = (pageNum < 1) ? 1 : pageNum;
        return (realPageNum - 1) * pageSize;
    }
}
