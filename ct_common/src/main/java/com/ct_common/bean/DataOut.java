package com.ct_common.bean;

import java.io.Closeable;

/**
 * @ClassName:DataOut
 * @Description:数据的输出
 * @Author:huge823865619
 * @Date:2022/7/2 21:06
 * @Version: 1.0
 */
public interface DataOut extends Closeable {
    //输出路径
    void setPath(String path);

    void write(Object obj) throws Exception;

    void write(String obj) throws Exception;
}