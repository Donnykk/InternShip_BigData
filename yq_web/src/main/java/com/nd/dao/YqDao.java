package com.nd.dao;

import com.nd.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YqDao {
    //查询疫情信息
    List<YqDataDay> findData(Map<String,Object> list);
    List<YqDataMonth> findDatam(Map<String,Object> list);
    List<YqDataDayAll> findDataAll(Map<String,Object> list);
    List<YqDataMonthAll> findDatamAll(Map<String,Object> list);
    QueryInfo findQueryInfo(Map<String,Object> list);
    YqDataMonth findDatamThis(Map<String,Object> list);
}
