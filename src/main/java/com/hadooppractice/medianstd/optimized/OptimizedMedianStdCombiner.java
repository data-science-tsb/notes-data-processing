package com.hadooppractice.medianstd.optimized;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.stream.StreamSupport;

public class OptimizedMedianStdCombiner extends Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable> {

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable> values, Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable>.Context context) throws IOException, InterruptedException {
        SortedMapWritable combined = StreamSupport
                .stream(values.spliterator(), false)
                .reduce(new SortedMapWritable(), (a, b) -> {
                    //we are not savages! we are thread-safe!
                    SortedMapWritable combinedMap = new SortedMapWritable(b);

                    //get all the values for map A
                    a.forEach((k, v) -> {
                        IntWritable currentValue = (IntWritable) combinedMap.putIfAbsent(k, v);
                        if (currentValue != v) {
                            Integer newValue = currentValue.get() + ((IntWritable) v).get();
                            combinedMap.put(k, new IntWritable(newValue));
                        }
                    });
                    return b;
                });

        context.write(key, combined);
    }
}
