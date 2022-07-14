package com.ct_producer.io;

import com.ct_common.bean.Data;
import com.ct_common.bean.DataIn;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:LocalDataIn
 * @Description:本地数据输入
 * @Author:huge823865619
 * @Date:2022/7/2 21:37
 * @Version: 1.0
 */
public class LocalFileDataIn implements DataIn {
    private BufferedReader read = null;//字符输入流

    public LocalFileDataIn(String path) {
        setPath(path);
    }

    @Override
    public void setPath(String path) {
        try {
            read = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object read() throws IOException {
        return null;
    }

    /**
     * 读取数据，返回集合
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    @Override
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        //写一个集合
        List<T> ts = new ArrayList();
        try {
            //从数据文件中读取所有数据
            String line = null;
            while ((line = read.readLine()) != null) {//读取数据不为空
                //将我们数据转换为指定类型的对象，封装为集合返回
                T t = clazz.newInstance();
                t.setValue(line);
                ts.add(t);
            }

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return ts;
    }

    @Override
    public void close() throws IOException {
        if (read != null) {
            read.close();
        }
    }
}
