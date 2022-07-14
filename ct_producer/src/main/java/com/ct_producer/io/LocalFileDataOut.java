package com.ct_producer.io;

import com.ct_common.bean.DataOut;

import java.io.*;

/**
 * @ClassName:LocalDataOut
 * @Description:本地文件数据输出
 * @Author:huge823865619
 * @Date:2022/7/2 21:37
 * @Version: 1.0
 */
public class LocalFileDataOut implements DataOut {
    private PrintWriter writer = null;

    public LocalFileDataOut(String path) {
        setPath(path);
    }

    @Override
    public void setPath(String path) {
        try {
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(Object obj) throws Exception {
        write(obj.toString());
    }

    /**
     * 将数据字符串生成到文件中
     *
     * @param data
     * @throws Exception
     */
    @Override
    public void write(String data) throws Exception {
        writer.println(data);
        writer.flush();//把流中的文件放到文件中
    }

    /**
     * 释放资源
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }
}
