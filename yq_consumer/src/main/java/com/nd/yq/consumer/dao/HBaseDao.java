package com.nd.yq.consumer.dao;

import com.nd.yq.common.bean.BaseHBaseDao;
import com.nd.yq.common.constact.Names;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseDao extends BaseHBaseDao {
    public void init() throws IOException {
        start();
        createNamespaceNX(Names.NAMESPACE.getValue());
        System.out.println("Create namespace success!");
        createTableTX(Names.TABLE.getValue(),
                      Names.CF_INFO.getValue());
        System.out.println("Create table success!");
        end();
    }

    public void insertData(String data) throws IOException {
        //数据分割
        String[] values = data.split(",", -1);
        String date = values[0];
        String province = values[1];
        String city = values[2];
        String confirmed = values[3];
        String suspected = values[4];
        String cured = values[5];
        String dead = values[6];
        //写Put对象
        String rowKey = getSalt(province, city, date) + "_" + date + "_" + province + "_" + city;
        Put put = new Put(Bytes.toBytes(rowKey));
        byte[] family = Bytes.toBytes(Names.CF_INFO.getValue());
        put.addColumn(family, Bytes.toBytes("date"), Bytes.toBytes(date));
        put.addColumn(family, Bytes.toBytes("province"), Bytes.toBytes(province));
        put.addColumn(family, Bytes.toBytes("city"), Bytes.toBytes(city));
        put.addColumn(family, Bytes.toBytes("confirmed"), Bytes.toBytes(confirmed));
        put.addColumn(family, Bytes.toBytes("suspected"), Bytes.toBytes(suspected));
        put.addColumn(family, Bytes.toBytes("cured"), Bytes.toBytes(cured));
        put.addColumn(family, Bytes.toBytes("dead"), Bytes.toBytes(dead));
        putData(Names.TABLE.getValue(), put);
    }
}
