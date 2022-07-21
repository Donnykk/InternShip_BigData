package com.nd.service;

import com.nd.entity.*;

import java.util.List;

public interface YqService {
    //查询疫情信息
    List<YqDataDay> findData(String province, String month);
    List<YqDataMonth> findDatam(String province);
    List<YqDataDayAll> findDataAll(String month);
    List<YqDataMonthAll> findDatamAll();
    QueryInfo findQueryInfo(String province,String month);
    YqDataMonth findDatamThis(String province,String month);
}
