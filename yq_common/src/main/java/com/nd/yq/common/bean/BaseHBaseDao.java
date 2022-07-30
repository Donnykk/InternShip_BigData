package com.nd.yq.common.bean;


import com.nd.yq.common.constact.Names;
import javafx.scene.control.Tab;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

public abstract class BaseHBaseDao {
    private ThreadLocal<Connection> connHolder = new ThreadLocal<>();
    private ThreadLocal<Admin> adminHolder = new ThreadLocal<>();
    //获取Admin对象
    protected synchronized Admin getAdmin() throws IOException {
        Admin admin = adminHolder.get();
        if (admin == null) {
            admin = getConnection().getAdmin();
            adminHolder.set(admin);
        }
        return admin;
    }
    //创建连接对象
    protected synchronized Connection getConnection() throws IOException {
        Connection conn = connHolder.get();
        if (conn == null) {
            Configuration conf = HBaseConfiguration.create();
            conf.set("hbase.zookeeper.quorum", "hadoop101,hadoop102,hadoop103");
            conn = ConnectionFactory.createConnection(conf);
            connHolder.set(conn);
        }
        return conn;
    }

    protected void start() throws IOException {
        getConnection();
        getAdmin();
    }

    protected void end() throws IOException {
        Admin admin = getAdmin();
        if (admin != null) {
            admin.close();
            adminHolder.remove();
        }
        Connection conn = getConnection();
        if (conn != null) {
            conn.close();
            connHolder.remove();
        }
    }

    protected void createNamespaceNX(String namespace) throws IOException {
        Admin admin = getAdmin();
        try {
            admin.getNamespaceDescriptor(namespace);
        } catch (NamespaceNotFoundException e) {
            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
            admin.createNamespace(namespaceDescriptor);
        }
    }
                
    protected void createTableTX(String tableName, String... families) throws IOException {
        Admin admin = getAdmin();
        if (admin.tableExists(TableName.valueOf(tableName))) {
            deleteTable(tableName);
        }
        createTable(tableName, families);
    }

    private void deleteTable(String name) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        admin.disableTable(tableName);
        admin.deleteTable(tableName);
    }
    
    private void createTable(String name, String... families) throws IOException {
        Admin admin = getAdmin();
        TableName tableName = TableName.valueOf(name);
        if (families == null || families.length == 0) {
            families = new String[1];
            families[0] = Names.CF_INFO.getValue();
        }
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
        for (String family : families) {
            hTableDescriptor.addFamily(new HColumnDescriptor(family));
        }
        admin.createTable(hTableDescriptor);
    }
    
    protected int getSalt(String province, String city, String date) {
        int provinceHash = province.hashCode();
        int cityHash = city.hashCode();
        int dateHash = date.hashCode();
        int crc = Math.abs(provinceHash ^ cityHash ^ dateHash);
        int saltValue = crc % 6;
        return saltValue;
    }
   
    protected void putData(String name, Put put) throws IOException {
        Connection conn = getConnection();
        Table table = conn.getTable(TableName.valueOf(name));
        table.put(put);
        table.close();
    }
}
