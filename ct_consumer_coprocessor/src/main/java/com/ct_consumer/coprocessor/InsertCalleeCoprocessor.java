package com.ct_consumer.coprocessor;

import com.ct_common.BaseHBaseDao;
import com.ct_common.constant.Names;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessor;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.coprocessor.RegionObserver;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.wal.WALEdit;

import java.io.IOException;
import java.util.Optional;

/**
 * @ClassName:InsertCalleeCoprocessor
 * @Description:使用协处理器保存被叫用户的数据
 * @Author:huge823865619
 * @Date:2022/7/10 9:20
 * @Version: 1.0
 */
public class InsertCalleeCoprocessor implements RegionCoprocessor, RegionObserver {
    /**
     * 保存主叫用户数据之后，由hbase自动保存被叫用户数据
     *
     * @param c
     * @param put
     * @param edit
     * @param durability
     * @throws IOException
     */
    @Override
    public void postPut(ObserverContext<RegionCoprocessorEnvironment> c, Put put, WALEdit edit, Durability durability) throws IOException {
        //获取表
        Table table = c.getEnvironment().getConnection().getTable(TableName.valueOf(Names.TABLE.getValue()));
        //主叫用户的rowKey
        String rowKey = Bytes.toString(put.getRow());
        //1_156_2022_133_1012_1
        String[] values = rowKey.split("_");
        String call1 = values[1];
        String call2 = values[3];
        String calltime = values[2];
        String duration = values[4];
        String flag = values[5];
        //只有主叫用户保存后才需要触发被叫用户的保存
        if ("1".equals(flag)) {
            CoprocessorDao dao = new CoprocessorDao();
            String calleeRowKey = dao.getRegionNum(call2, calltime) + "_" + call2 + "_" +
                    calltime + "_" + call1 + "_" + duration + "_0";
            //保存数据
            Put calleePut = new Put(Bytes.toBytes(calleeRowKey));
            byte[] calleeFamily = Bytes.toBytes(Names.CF_CALLEE.getValue());
            calleePut.addColumn(calleeFamily, Bytes.toBytes("call2"), Bytes.toBytes(call2));
            calleePut.addColumn(calleeFamily, Bytes.toBytes("call1"), Bytes.toBytes(call1));
            calleePut.addColumn(calleeFamily, Bytes.toBytes("calltime"), Bytes.toBytes(calltime));
            calleePut.addColumn(calleeFamily, Bytes.toBytes("duration"), Bytes.toBytes(duration));
            calleePut.addColumn(calleeFamily, Bytes.toBytes("flag"), Bytes.toBytes("0"));
            System.out.println(calleePut);
            table.put(calleePut);
        }
        //关闭表
        table.close();
    }

    @Override
    public Optional<RegionObserver> getRegionObserver() {
        return Optional.of(this);
    }

    private static class CoprocessorDao extends BaseHBaseDao {
        public int getRegionNum(String tell, String time) {
            return genRegionNum(tell, time);
        }
    }
}
