package com.nd.yq.analysis;

import com.nd.yq.analysis.tool.AnalysisTool;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author NirVana
 * @version 1.0
 * @package com.nd.yq.analysis.ByMonth
 * @create 2022-07-18 11:04
 * @discrpit 按月计数，每个月，全国范围内确诊、疑似、治愈、死亡
 */
public class AnalysisData {
    public static void main(String[] args) throws Exception {
        ToolRunner.run((Tool) new AnalysisTool(),args);
    }
}
