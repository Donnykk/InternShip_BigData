package com.nd.yq.common.util;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;

import java.util.HashMap;

/**
 * @author NirVana
 * @version 1.0
 * @package PACKAGE_NAME
 * @create 2022-07-18 9:21
 * @discrpit TODO
 */
public class HBaseUtil {
    public static void getValue(Cell cell, HashMap<String,String> cfValueMap){
        //一个colFamily里有多个qualifier
        String qualifier = new String(CellUtil.cloneQualifier(cell));
        String quaValue = new String(CellUtil.cloneValue(cell));
        System.out.println("qualifier:"+qualifier+"quaValue:"+quaValue);
        cfValueMap.put(qualifier,quaValue);
    }

}
