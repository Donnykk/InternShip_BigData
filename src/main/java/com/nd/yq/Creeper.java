package com.nd.yq;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Creeper {
    public static void main(String[] args) throws IOException {
        String url = "https://raw.githubusercontent.com/canghailan/Wuhan-2019-nCoV/master/Wuhan-2019-nCoV.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream(),
                StandardCharsets.UTF_8));
        PrintWriter writer = new PrintWriter(new FileWriter("covid.csv"));
        String line;
        String outStr;
        String[] data;
        try {
            while (true) {
                if ((line = br.readLine()) == null)
                    break;
                data = line.split(",", -1);
                if (Objects.equals(data[2], "CN") && !Objects.equals(data[3], "") && !Objects.equals(data[5], "")) {
                    if (data[0].startsWith("2020-01") || data[0].startsWith("2020-02") || data[0].startsWith("2020-03")) {
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf2.parse(data[0]);
                        data[0] = sdf1.format(date);
                        outStr = data[0] + "," + data[3] + "," + data[5] + "," + data[7] + "," + data[8] + "," + data[9] + "," + data[10];
                        System.out.println(outStr);
                        writer.println(outStr);
                    } else if (data[0].startsWith("2020-04")) {
                        break;
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        writer.flush();
        writer.close();
    }
}


