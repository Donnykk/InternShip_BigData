package com.nd.yq.analysis.reducer;

import org.apache.commons.math3.analysis.function.Exp;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author NirVana
 * @version 1.0
 * @package com.nd.yq.analysis.reducer
 * @create 2022-07-18 8:41
 * @discrpit Reducer
 */
public class MySQLTextReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
        int sumConfirmed = 0;
        int sumSuspected = 0;
        int sumCured = 0;
        int sumDead = 0;
        int tdConfirmed = 0;
        int incConfirmed = 0;
        int incDead = 0;
        int incCured = 0;

        boolean byMonth = !(key.toString().length()>6&&key.toString().charAt(6)>='0'&&key.toString().charAt(6)<='9');

        for (Text value : values) {
            //isSub or not
            String[] data = value.toString().split(",");
            boolean wheToSub;
            String[] split;
            if(byMonth) {
                wheToSub = data[1].startsWith("sub");
                split = wheToSub ? data[1].substring(3).split("_") : data[1].split("_");
            }
            else{
                wheToSub = data[0].startsWith("sub");
                split = wheToSub ? data[0].substring(3).split("_") : data[0].split("_");
            }
            //get 4 numbers and date
            int confirmed = 0;
            int suspected = 0;
            int cured = 0;
            int dead = 0;
            try {
                confirmed = Integer.parseInt(split[0]);
                suspected = Integer.parseInt(split[1]);
                cured = Integer.parseInt(split[2]);
                dead = Integer.parseInt(split[3]);
            } catch (Exception e) {
                return;
            }
            /*
             * calculate
             * */
            /* totally 4 kinds of keys:
             * 1. 20200101
             * 2. 20200101 + province
             * 3. 020200101
             * 4. 020200101 + province
             * */
            if(!byMonth||data[0].substring(6,8).equals("01")) {
                if (wheToSub) {
                    incConfirmed -= confirmed;
                    incCured -= cured;
                    incDead -= dead;
                } else {
                    sumConfirmed += confirmed;
                    sumCured += cured;
                    sumDead += dead;

                    incConfirmed += confirmed;
                    incCured += cured;
                    incDead += dead;
                }
            }
            if (!wheToSub) {
                sumSuspected += suspected;
            }
        }
        //current confirmed
        tdConfirmed = sumConfirmed - sumCured - sumDead;

        sumConfirmed = sumConfirmed<0?0:sumConfirmed;
        sumSuspected = sumSuspected<0?0:sumSuspected;
        sumCured = sumCured<0?0:sumCured;
        sumDead = sumDead<0?0:sumDead;
        incConfirmed = incConfirmed<0?0:incConfirmed;
        incCured = incCured<0?0:incCured;
        incDead = incDead<0?0:incDead;
        tdConfirmed = tdConfirmed<0?0:tdConfirmed;

        context.write(key,new Text(sumConfirmed + "_" + sumSuspected + "_" + sumCured + "_" + sumDead + "_" + tdConfirmed + "_" + incConfirmed + "_" + incCured + "_" + incDead));
    }

}
