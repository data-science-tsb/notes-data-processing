package com.hadooppractice.average;

import com.hadooppractice.utils.XMLParser;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by lbibera on 08/01/2017.
 */
public class AverageMapper extends Mapper<Object, Text, Text, AverageTuple> {

    @Override
    protected void map(Object key, Text value, Mapper<Object, Text, Text, AverageTuple>.Context context) throws IOException, InterruptedException {
        Map<String, String> parsed = XMLParser.toMap(value.toString());

        AverageTuple average = new AverageTuple();
        average.setCount(1L);
        average.setAverageLength(1.0*parsed.get("Text").length());

        context.write(new Text(parsed.get("UserId")), average);
    }
}
