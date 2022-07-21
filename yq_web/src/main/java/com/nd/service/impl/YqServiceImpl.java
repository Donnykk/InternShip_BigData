package com.nd.service.impl;

import com.nd.dao.YqDao;
import com.nd.entity.*;
import com.nd.service.YqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service//表示是业务层
public class YqServiceImpl implements YqService {
    @Autowired
    private YqDao yqDao;
    @Override
    public List<YqDataDay> findData(String province, String month) {
        Map<String,Object> map = new HashMap<>();
        map.put("province",province);
        map.put("month",month);
        List<YqDataDay> data = yqDao.findData(map);
        return data;
    }

    @Override
    public List<YqDataMonth> findDatam(String province) {
        Map<String,Object> map = new HashMap<>();
        map.put("province",province);
        List<YqDataMonth> data = yqDao.findDatam(map);
        return data;
    }

    @Override
    public List<YqDataDayAll> findDataAll(String month) {
        Map<String,Object> map = new HashMap<>();
        map.put("month",month);
        List<YqDataDayAll> data = yqDao.findDataAll(map);
        return data;
    }

    @Override
    public List<YqDataMonthAll> findDatamAll() {
        Map<String,Object> map = new HashMap<>();
        List<YqDataMonthAll> data = yqDao.findDatamAll(map);
        return data;
    }

    @Override
    public QueryInfo findQueryInfo(String province, String month) {
        Map<String,Object> map = new HashMap<>();
        map.put("province",province);
        map.put("month",month);
        QueryInfo data = yqDao.findQueryInfo(map);
        return data;
    }

    @Override
    public YqDataMonth findDatamThis(String province, String month) {
        Map<String,Object> map = new HashMap<>();
        map.put("province",province);
        map.put("month",month);
        YqDataMonth data=yqDao.findDatamThis(map);
        return data;
    }
}
