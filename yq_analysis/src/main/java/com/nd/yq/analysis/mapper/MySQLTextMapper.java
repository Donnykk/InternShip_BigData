package com.nd.yq.analysis.mapper;

import com.nd.yq.common.constact.Names;
import com.nd.yq.common.util.DateTransfer;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;

import java.io.IOException;


/**
 * @author NirVana
 * @version 1.0
 * @package com.nd.yq.analysis
 * @create 2022-07-18 0:00
 * @discrpit mapper
 */
public class MySQLTextMapper extends TableMapper<Text,Text> {
    @Override
    protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        //Salt_date_province_city
        //confirmed_suspected_cured_dead

        //firstly get key and value
        String rowKey = Bytes.toString(key.get());
        String[] values = rowKey.split("_");
        //a day split into two keys
        String date = values[1];
        String nextDay = DateTransfer.getNextDay(date);
        String nextMonth = DateTransfer.getNextMonth(date);
        String province = values[2];
        //get 4 numbers
        String confirmed = new String(value.getValue(Bytes.toBytes(Names.CF_INFO.getValue()), Bytes.toBytes("confirmed")), "UTF-8");
        String suspected = new String(value.getValue(Bytes.toBytes(Names.CF_INFO.getValue()), Bytes.toBytes("suspected")), "UTF-8");
        String cured = new String(value.getValue(Bytes.toBytes(Names.CF_INFO.getValue()), Bytes.toBytes("cured")));
        String dead = new String(value.getValue(Bytes.toBytes(Names.CF_INFO.getValue()), Bytes.toBytes("dead")));
        //the values
        String data = confirmed + "_" + suspected + "_" + cured + "_" + dead;
        /*
         * 1.date + province
         * 2.nextdate + province
         * in 2, add 'sub' fix
         * */
        //byday
        context.write(new Text(date), new Text(data));
        context.write(new Text(nextDay), new Text("sub" + data));
        context.write(new Text(date + province), new Text(data));
        context.write(new Text(nextDay + province), new Text("sub" + data));
        //bymonth
        context.write(new Text(date.substring(0,6)), new Text(date+","+data));
        context.write(new Text(nextMonth.substring(0,6)), new Text(date+",sub" + data));
        context.write(new Text(date.substring(0,6) + province), new Text(date+","+data));
        context.write(new Text(nextMonth.substring(0,6) + province), new Text(date+",sub" + data));

    }
}
