package com.ct_common;

import com.ct_common.constant.Names;
import com.ct_common.constant.ValueConstant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:BaseDao
 * @Description:基础数据访问对象
 * @Author:huge823865619
 * @Date:2022/7/3 19:34
 * @Version: 1.0
 */
public abstract class BaseHBaseDao {
    //创建线程访问对象
    private final ThreadLocal<Connection> connHolder = new ThreadLocal<>();
    private final ThreadLocal<Admin> adminHolder = new ThreadLocal<>();

    /**
     * 开始
     */
    protected void start() throws IOException {
        getConnect();//获取连接
        getAdmin();//获取admin
    }

    /**
     * 结束
     *
     * @throws IOException
     */
    protected void end() throws IOException {
        Admin admin = getAdmin();
        if (admin != null) {
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnect();
        if (conn != null) {
            conn.close();
            connHolder.remove();
        }
    }

    /**
     * 创建表，没有预分区
     *
     * @param name
     * @param families
     */
    protected void createTableXX(String name, String... families) throws IOException {
        createTableTX(name, null, families);
    }

    /**
     * 创建表，有预分区
     *
     * @param name
     * @param regionCount
     * @param families
     * @throws IOException
     */
    protected void createTableTX(String name, Integer regionCount, String... families) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        if (admin.tableExists(tableName)) {
            //表存在，删除表
            deleteTable(name);
        }
        //创建表
        createTable(name, regionCount, families);
    }

    /**
     * 创建表
     *
     * @param name
     * @param regionCount
     * @param families
     * @throws IOException
     */
    private void createTable(String name, Integer regionCount, String... families) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        //表的描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        //列族
        if (families.length == 0) {
            families = new String[1];
            families[0] = Names.CF_INFO.getValue();
        }
        for (String family : families) {
            //列的描述器
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(family);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        //增加预分区
        //分区键
        if (regionCount == null || regionCount <= 0) {//没有分区键
            admin.createTable(hTableDescriptor);
        } else {//有分区键
            byte[][] splitKeys = genSplitKeys(regionCount);
            admin.createTable(hTableDescriptor, splitKeys);
        }
    }

    /**
     * 生成分区键
     *
     * @param regionCount
     * @return
     */
    private byte[][] genSplitKeys(int regionCount) {
        int splitKeyCount = regionCount - 1;//如果有6个分区，只有5个分区键
        byte[][] bs = new byte[splitKeyCount][];//写一个二维数组,为分区键
        //分区键:0|,1|,2|,3|,4|
        //[负无穷,0|),[0|,1|),[1|,2|),[2|,3|),[3|,4|),[4|,正无穷)
        List<byte[]> bsList = new ArrayList<byte[]>();
        for (int i = 0; i < splitKeyCount; i++) {
            String splitKey = i + "|";
            System.out.println(splitKey);
            bsList.add(Bytes.toBytes(splitKey));
        }
        bsList.toArray(bs);
        return bs;
    }

    /**
     * 计算分区号
     *
     * @param tel
     * @param date
     * @return
     */
    protected static int genRegionNum(String tel, String date) {
        //15623513131,取后四位没有规律的
        String userCode = tel.substring(tel.length() - 4);
        //20220707120000,获取年月
        String yearMonth = date.substring(0, 6);
        //实现散列
        int userCodeHash = userCode.hashCode();
        int yearMonthHash = yearMonth.hashCode();
        //crc校验采用异或算法
        int crc = Math.abs(userCodeHash ^ yearMonthHash);
        //取模
        return crc % ValueConstant.REGION_COUNT;
    }

    protected void putData(String value, Put put) throws IOException {
        //获取表对象
        Connection connection = getConnect();
        Table table = connection.getTable(TableName.valueOf(value));
        table.put(put);
        table.close();
    }

    /**
     * 删除表
     *
     * @param name
     * @throws IOException
     */
    protected void deleteTable(String name) throws IOException {
        TableName tableName = TableName.valueOf(name);
        Admin admin = getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }

    /**
     * 创建命名空间，如果命名空间已经存在，不需要创建，否则，创建新的
     *
     * @param namespace
     */
    protected void createNamespaceNX(String namespace) throws IOException {
        Admin admin = getAdmin();
        try {
            //获取命名空间描述器
            admin.getNamespaceDescriptor(namespace);
        } catch (NamespaceNotFoundException e) {
            //e.printStackTrace();
            //创建命名空间描述器
            NamespaceDescriptor namespaceDescriptor =
                    NamespaceDescriptor.create(namespace).build();
            //创建命名空间
            admin.createNamespace(namespaceDescriptor);
        }

    }

    /**
     * 获取管理员admin
     *
     * @return
     * @throws IOException
     */
    protected synchronized Admin getAdmin() throws IOException {
        Admin admin = adminHolder.get();
        if (admin == null) {
            admin = getConnect().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }

    /**
     * 创建连接对象
     *
     * @return
     */
    //synchronized:表示同步
    protected synchronized Connection getConnect() throws IOException {
        //获取连接对象
        Connection conn = connHolder.get();
        if (conn == null) {//判断连接对象是否存在
            Configuration conf = HBaseConfiguration.create();
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }
}