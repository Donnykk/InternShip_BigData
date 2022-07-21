package com.nd.controller;

import com.nd.entity.*;
import com.nd.service.YqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
@Controller//控制层
@RequestMapping("/yq/")
public class YqController {
    @Autowired//表示控制层调用业务层
    private YqService yqService;
    @RequestMapping("query.do")
    public String query(){
        return "query";
    }
    //展示疫情信息
    @RequestMapping("view.do")
    public String view(Model model,
                       @RequestParam(name = "province",defaultValue = "")String province,
                       @RequestParam(name = "month",defaultValue = "")String month){
        List<YqDataDay> data = yqService.findData(province,month);
        List<YqDataMonth> datam = yqService.findDatam(province);
        List<YqDataDayAll> dataAll = yqService.findDataAll(month);
        List<YqDataMonthAll> datamAll = yqService.findDatamAll();
        QueryInfo info=yqService.findQueryInfo(province,month);
        YqDataMonth dataMonth=yqService.findDatamThis(province,month);
        model.addAttribute("yqDataDay",data);
        model.addAttribute("yqDataMonth",datam);
        model.addAttribute("yqDataDayAll",dataAll);
        model.addAttribute("yqDataMonthAll",datamAll);
        model.addAttribute("queryInfo",info);
        model.addAttribute("yqDataMonthThis",dataMonth);
        return "view";
    }
}
