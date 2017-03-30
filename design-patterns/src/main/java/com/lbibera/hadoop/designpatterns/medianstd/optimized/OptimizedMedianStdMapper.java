package com.lbibera.hadoop.designpatterns.medianstd.optimized;


import com.lbibera.hadoop.designpatterns.utils.DateConverter;
import com.lbibera.hadoop.designpatterns.utils.XMLParser;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class OptimizedMedianStdMapper extends Mapper<Object, Text, IntWritable, SortedMapWritable<IntWritable>> {

    private static final String FIELD_DATE = "CreationDate";
    private static final String FIELD_COMMENT = "Text";
    private static final IntWritable ONE = new IntWritable(1);

    @Override
    protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, SortedMapWritable<IntWritable>>.Context context) throws IOException, InterruptedException {
        Map<String, String> parsed = XMLParser.toMap(value);

        if (isNotValid(parsed)) return;

        Integer commentLength = parsed.get(FIELD_COMMENT).length();

        IntWritable outKey = new IntWritable();
        outKey.set(DateConverter
                .stringToDate(parsed.get(FIELD_DATE))
                .getHour());

        SortedMapWritable outValue = new SortedMapWritable();
        outValue.put(new IntWritable(commentLength), ONE);

        context.write(outKey, outValue);
    }

    private boolean isNotValid(Map<String, String> parsed) {
        return parsed.get(FIELD_COMMENT) != null
                && !parsed.get(FIELD_COMMENT).isEmpty()
                && parsed.get(FIELD_DATE) != null
                && !parsed.get(FIELD_DATE).isEmpty();
    }

}
