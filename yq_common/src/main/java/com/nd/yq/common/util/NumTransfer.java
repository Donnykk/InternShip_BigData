package com.nd.yq.common.util;

/**
 * @author NirVana
 * @version 1.0
 * @package HBaseUtil
 * @create 2022-07-18 9:47
 * @discrpit TODO
 */
public class NumTransfer {
    public static String numTrans (String confirmed,String suspected,String cured,String dead){
        String str1 = String.format("%05d", Integer.parseInt(confirmed));
        String str2 = String.format("%05d", Integer.parseInt(suspected));
        String str3 = String.format("%05d", Integer.parseInt(cured));
        String str4 = String.format("%05d", Integer.parseInt(dead));
        return str1+str2+str3+str4;
    }
    public static String numTrans (int confirmed,int suspected,int cured,int dead){
        String str1 = String.format("%05d", confirmed);
        String str2 = String.format("%05d", suspected);
        String str3 = String.format("%05d", cured);
        String str4 = String.format("%05d", dead);
        return str1+str2+str3+str4;
    }

}
