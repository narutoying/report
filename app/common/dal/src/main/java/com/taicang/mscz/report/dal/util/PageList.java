package com.taicang.mscz.report.dal.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 包含“分页”信息的<code>List</code>。
 *
 * @author Michael Zhou
 * @version $Id: PageList.java,v 1.1 2005/11/08 14:01:56 calvin Exp $
 */
public class PageList extends ArrayList {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3257568390985103409L;
    private Paginator paginator;

    /**
     * 创建一个<code>PageList</code>。
     */
    public PageList() {
        paginator = new Paginator();
    }

    /**
     * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
     *
     * @param c 要复制的<code>Collection</code>
     */
    public PageList(Collection c) {
        this(c, null);
    }

    /**
     * 创建<code>PageList</code>，并将指定<code>Collection</code>中的内容复制到新的list中。
     *
     * @param c 要复制的<code>Collection</code>
     */
    public PageList(Collection c, Paginator paginator) {
        super(c);
        this.paginator = (paginator == null) ? new Paginator()
                                             : paginator;
    }

    /**
     * 取得分页器，可从中取得有关分页和页码的所有信息。
     *
     * @return 分页器对象
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * 设置分页器。
     *
     * @param paginator 要设置的分页器对象
     */
    public void setPaginator(Paginator paginator) {
        if (paginator != null) {
            this.paginator = paginator;
        }
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public int getPageSize() {
        return paginator.getItemsPerPage();
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public int getTotalItem() {
        return paginator.getItems();
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public int getTotalPage() {
        return paginator.getPages();
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public void setPageSize(int i) {
        paginator.setItemsPerPage(i);
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public void setTotalItem(int i) {
        paginator.setItems(i);
    }

    /**
     * @deprecated use getPaginator() instead
     */
    public void setTotalPage(int i) {
        setTotalItem(i * getPageSize());
    }
}
