package com.nd.yq.analysis.tool;

import com.nd.yq.analysis.io.MySQLTextOutputFormat;
import com.nd.yq.analysis.mapper.MySQLTextMapper;
import com.nd.yq.analysis.reducer.MySQLTextReducer;
import com.nd.yq.common.constact.Names;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;


/**
 * @author NirVana
 * @version 1.0
 * @package com.nd.yq.analysis
 * @create 2022-07-18 0:16
 * @discrpit analysis tool
 */
public class AnalysisTool implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        //get job
        Job job = Job.getInstance();
        //set jar
        job.setJarByClass(AnalysisTool.class);
        Scan scan = new Scan();
        scan.addFamily(Bytes.toBytes(Names.CF_INFO.getValue()));
        //set mapper
        TableMapReduceUtil.initTableMapperJob(
                Names.TABLE.getValue(),
                scan,
                MySQLTextMapper.class,
                Text.class,
                Text.class,
                job
        );
        //set reducer
        job.setReducerClass(MySQLTextReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        //set outputFormat
        job.setOutputFormatClass(MySQLTextOutputFormat.class);
        //submit
        boolean result = job.waitForCompletion(true);
        if(result){
            return JobStatus.State.SUCCEEDED.getValue();
        }
        else{
            return JobStatus.State.FAILED.getValue();
        }
    }

    @Override
    public void setConf(Configuration conf) {

    }

    @Override
    public Configuration getConf() {
        return null;
    }
}
