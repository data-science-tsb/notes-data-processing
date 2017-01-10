package com.hadooppractice.medianstd;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianStdReducer extends Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>{

	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values, Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>.Context context) throws IOException, InterruptedException {
	
	}
}
