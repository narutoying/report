/**
 * narutoying09@gmail.com
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package com.taicang.mscz.report.test.integration;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: BaseTestCase.java, v 0.1 2013-4-9 下午10:03:15
 *          narutoying09@gmail.com Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/*.xml" })
public class BaseTestCase extends TestCase {

}
