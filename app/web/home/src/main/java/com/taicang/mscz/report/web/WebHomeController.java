package com.taicang.mscz.report.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

@Controller
public class WebHomeController {

    private static Logger      logger = LoggerFactory.getLogger(WebHomeController.class);

    @RequestMapping(value = "/test.htm")
    public void test(ModelAndView mv, ModelMap map, HttpServletRequest req) {
        map.addAttribute("request", req);
    }

}
