package com.nd.yq;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Creeper {
    public static void main(String[] args) throws IOException {
        String url = "https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv";
        BufferedReader br = null;
        CSVWriter writer = new CSVWriter(new FileWriter("covid.csv"));
        try {
            br = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line;
        String[] data;
        data = new String[]{"日期", "省份", "城市/来源", "新增确诊", "疑似确诊", "痊愈", "死亡"};
        writer.writeNext(data);
        try {
            while (true) {
                assert br != null;
                if ((line = br.readLine()) == null) break;
                data = line.split(",");
                if (Objects.equals(data[2], "CN") && !Objects.equals(data[3], "") && !Objects.equals(data[5], "")) {
                    if (data[0].startsWith("2020-01") || data[0].startsWith("2020-02") || data[0].startsWith("2020-03")) {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf2.parse(data[0]);
                        data[0] = sdf1.format(date);
                        List<String> list = Arrays.asList(data);
                        List<String> arrList = new ArrayList<>(list);
                        arrList.remove(1);
                        arrList.remove(1);
                        arrList.remove(2);
                        arrList.remove(3);
                        System.out.println(arrList);
                        data = arrList.toArray(new String[arrList.size()]);
                        writer.writeNext(data);
                    } else if (data[0].startsWith("2020-05")) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}