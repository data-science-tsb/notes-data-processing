package com.hadooppractice.medianstd.optimized;

import com.hadooppractice.utils.SortedMapWritableUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class OptimizedMedianStdCombiner extends Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable> values, Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable>.Context context) throws IOException, InterruptedException {
        SortedMapWritable combined = StreamSupport
                .stream(values.spliterator(), false)
                .reduce(new SortedMapWritable(), SortedMapWritableUtil::combineMap);

        context.write(key, combined);
    }
}
