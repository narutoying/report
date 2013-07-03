/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.common.dal.daointerface;

import java.util.List;

import com.taicang.mscz.report.common.dal.dataobject.UnitDO;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: UnitExtraDAO.java, v 0.1 2013-7-3 下午3:52:49
 *          narutoying09@gmail.com Exp $
 */
public interface UnitExtraDAO {
	List<UnitDO> getByCondition(String sql);
}
