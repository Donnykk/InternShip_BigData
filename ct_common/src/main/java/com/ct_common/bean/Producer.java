package com.ct_common.bean;

import java.io.Closeable;
import java.io.IOException;

/**
 * @InterfaceName:Producer
 * @Description:生产者接口
 * @Author:huge823865619
 * @Date:2022/7/2 21:05
 * @Version: 1.0
 */
public interface Producer extends Closeable {
    //数据输入
    void setIn(DataIn in);

    //数据输出
    void setOut(DataOut out);

    //生产数据
    void produce() throws IOException;
}