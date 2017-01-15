package com.hadooppractice.medianstd.optimized;

import com.hadooppractice.medianstd.MedianStdTuple;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class OptimizedMedianStdReducer extends Reducer<IntWritable, SortedMapWritable, IntWritable, MedianStdTuple> {

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable> values, Reducer<IntWritable, SortedMapWritable, IntWritable, MedianStdTuple>.Context context) throws IOException, InterruptedException {
    }
}
