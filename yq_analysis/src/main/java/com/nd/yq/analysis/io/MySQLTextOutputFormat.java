package com.nd.yq.analysis.io;

import com.nd.yq.common.util.DateTransfer;
import com.nd.yq.common.util.JdbcUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitter;
import org.apache.hadoop.mapreduce.lib.output.PathOutputCommitterFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author NirVana
 * @version 1.0
 * @package com.nd.yq.analysis.ByMonth
 * @create 2022-07-18 10:25
 * @discrpit mysql格式化输出对象
 */
public class MySQLTextOutputFormat extends OutputFormat<Text, Text> {

    protected static class MySQLRecordWriter extends RecordWriter<Text,Text>{
        private Connection conn = null;
        public MySQLRecordWriter(){
            conn = JdbcUtil.getConnection();
        }

        //对标reducer的output
        @Override
        public void write(Text key, Text value) throws IOException, InterruptedException {
            //获取4个sum
            String[] values = value.toString().split("_");
            String sumConfirmed = values[0];
            String sumSuspected = values[1];
            String sumCured = values[2];
            String sumDead = values[3];
            String tdConfirmed = values[4];
            String incConfirmed = values[5];
            String incCured = values[6];
            String incDead = values[7];
            PreparedStatement ps = null;
            boolean byMonth = !(key.toString().length()>6&&key.toString().charAt(6)>='0'&&key.toString().charAt(6)<='9');
            try {
                //如果date有八位
                if(!byMonth) {
                    //sql
                    String insertSql = "insert into tb_sumup_byday(date,date6,province,sumConfirmed,sumSuspected,sumCured,sumDead,tdConfirmed,incConfirmed,incCured,incDead) values(?,?,?,?,?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(insertSql);
                    ps.setString(1, DateTransfer.dateTrans(key.toString().substring(0, 8), "yyyyMMdd", "yyyy-MM-dd"));
                    ps.setString(2, key.toString().substring(0, 6));
                    if (key.toString().substring(8).equals("")) {
                        ps.setString(3, "中国");
                    } else {
                        ps.setString(3, key.toString().substring(8));
                    }
                    ps.setInt(4, Integer.parseInt(sumConfirmed));
                    ps.setInt(5, Integer.parseInt(sumSuspected));
                    ps.setInt(6, Integer.parseInt(sumCured));
                    ps.setInt(7, Integer.parseInt(sumDead));
                    ps.setInt(8, Integer.parseInt(tdConfirmed));
                    ps.setInt(9, Integer.parseInt(incConfirmed));
                    ps.setInt(10, Integer.parseInt(incCured));
                    ps.setInt(11, Integer.parseInt(incDead));
                    //exec
                    if(!key.toString().substring(4,8).equals("1209") && !key.toString().substring(4,6).equals("12"))
                        ps.executeUpdate();
                }
                //date只有六位
                else {
                    String insertSql2 = "insert into tb_sumup_bymonth(date,province,sumConfirmed,sumSuspected,sumCured,sumDead,tdConfirmed,incConfirmed,incCured,incDead) values(?,?,?,?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(insertSql2);
                    ps.setString(1, key.toString().substring(0, 6));
                    if (key.toString().substring(6).equals("")) {
                        ps.setString(2, "中国");
                    } else {
                        ps.setString(2, key.toString().substring(6));
                    }
                    ps.setInt(3, Integer.parseInt(sumConfirmed));
                    ps.setInt(4, Integer.parseInt(sumSuspected));
                    ps.setInt(5, Integer.parseInt(sumCured));
                    ps.setInt(6, Integer.parseInt(sumDead));
                    ps.setInt(7, Integer.parseInt(tdConfirmed));
                    ps.setInt(8, Integer.parseInt(incConfirmed));
                    ps.setInt(9, Integer.parseInt(incCured));
                    ps.setInt(10, Integer.parseInt(incDead));
                    //exec
                    if(!key.toString().substring(0,6).equals("202101") && !key.toString().substring(0,6).equals("202012"))
                        ps.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                if(ps!=null){
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public RecordWriter<Text, Text> getRecordWriter(TaskAttemptContext context) throws IOException, InterruptedException {
        return new MySQLRecordWriter();
    }

    @Override
    public void checkOutputSpecs(JobContext context) throws IOException, InterruptedException {

    }

    public static Path getOutputPath(JobContext job){
        String name = job.getConfiguration().get(FileOutputFormat.OUTDIR);
        return name==null? null:new Path(name);
    }

    private PathOutputCommitter committer = null;

    @Override
    public OutputCommitter getOutputCommitter(TaskAttemptContext context) throws IOException, InterruptedException {
        if(committer == null){
            Path output = getOutputPath(context);
            committer = PathOutputCommitterFactory.getCommitterFactory(
                    output,
                    context.getConfiguration()).createOutputCommitter(output,context);
        }
        return committer;
    }
}
