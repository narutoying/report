/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.common.dal.extra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.taicang.mscz.report.common.dal.daointerface.UnitExtraDAO;
import com.taicang.mscz.report.common.dal.dataobject.UnitDO;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: UnitExtraDAOImpl.java, v 0.1 2013-7-3 下午3:54:14
 *          narutoying09@gmail.com Exp $
 */
public class UnitExtraDAOImpl extends SqlMapClientDaoSupport implements
		UnitExtraDAO {

	/**
	 * @see com.taicang.mscz.report.common.dal.daointerface.UnitExtraDAO#getByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UnitDO> getByCondition(String sql) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("sql", sql);
		return (List<UnitDO>) getSqlMapClientTemplate().queryForList(
				"EXTRA-UNIT-GET-BY-CONDITION", params);
	}

}
