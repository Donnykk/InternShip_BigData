package com.ct_consumer.bean;

import com.ct_common.BaseHBaseDao;
import com.ct_common.constant.Names;
import com.ct_common.constant.ValueConstant;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HBaseDao extends BaseHBaseDao {
    public void init() throws IOException {
        start();
        createNamespaceNX(Names.NAMESPACE.getValue());
        createTableTX(Names.TABLE.getValue(),
                "com.ct_consumer.coprocessor.InsertCalleeCoprocessor",
                ValueConstant.REGION_COUNT,
                Names.CF_CALLER.getValue(),
                Names.CF_CALLEE.getValue());
        end();
    }

    /**
     * 插入数据
     *
     * @param value
     */
    public void insertData(String value) throws IOException {
        //将通话日志保存到HBase表中
        //1.获取通话日志数据
        String[] values = value.split("\t");
        String call1 = values[0];
        String call2 = values[1];
        String calltime = values[2];
        String duration = values[3];
        //2.创建数据对象
        /*
        rowKey设计
        (1)长度原则
            最大长值64KB,推荐长为10~100byte
            最好8的倍数,能短则短，如果rowkey太长会影响存储性能
        (2)唯一原则:rowKey应该具备唯一性
        (3)散列原则
            盐值散列：不能使用时间戳直接作为rowKey,会导致数据倾斜,在rowkey前加随机数
            字符串反转:可以在时间戳反转,用的最多的是地方在时间戳和电话号码
                      15623513131=>13131532651
            计算分区号:让分区号没有规律就可以,hashMap
        */
        //rowKey=regionNum+call1+time+call2+duration
        //主叫用户
        String rowKey = genRegionNum(call1, calltime) + "_" + call1 + "_"
                + calltime + "_" + call2 + "_" + duration + "_1";
        Put put = new Put(Bytes.toBytes(rowKey));
        byte[] family = Bytes.toBytes(Names.CF_CALLER.getValue());
        //增加列
        put.addColumn(family, Bytes.toBytes("call1"), Bytes.toBytes(call1));
        put.addColumn(family, Bytes.toBytes("call2"), Bytes.toBytes(call2));
        put.addColumn(family, Bytes.toBytes("calltime"), Bytes.toBytes(calltime));
        put.addColumn(family, Bytes.toBytes("duration"), Bytes.toBytes(duration));
        put.addColumn(family, Bytes.toBytes("flag"), Bytes.toBytes("1"));
        //被叫用户
        String calleeRowKey = genRegionNum(call2, calltime) + "_" + call2 + "_"
                + calltime + "_" + call1 + "_" + duration + "_0";
        Put calleePut = new Put(Bytes.toBytes(calleeRowKey));
        byte[] calleeFamily = Bytes.toBytes(Names.CF_CALLEE.getValue());
        //增加列
        calleePut.addColumn(calleeFamily, Bytes.toBytes("call2"), Bytes.toBytes(call2));
        calleePut.addColumn(calleeFamily, Bytes.toBytes("call1"), Bytes.toBytes(call1));
        calleePut.addColumn(calleeFamily, Bytes.toBytes("calltime"), Bytes.toBytes(calltime));
        calleePut.addColumn(calleeFamily, Bytes.toBytes("duration"), Bytes.toBytes(duration));
        calleePut.addColumn(calleeFamily, Bytes.toBytes("flag"), Bytes.toBytes("0"));

        //3.保存数据
        List<Put> puts = new ArrayList<Put>();
        puts.add(put);
        puts.add(calleePut);
        putData(Names.TABLE.getValue(), puts);
    }

    /**
     * 插入对象
     *
     * @param log
     * @throws IOException
     */
    public void insertData(CallLog log) throws IOException, IllegalAccessException {
        log.setRowKey(genRegionNum(log.getCall1(), log.getCalltime()) + "_" + log.getCall1() + "_"
                + log.getCalltime() + "_" + log.getCall2() + "_" + log.getDuration());
        putData(log);
    }
}
