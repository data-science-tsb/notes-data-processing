package com.hadooppractice.medianstd;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianStdReducer extends Reducer<Text, IntWritable, Text, MedianStdTuple>{

	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, MedianStdTuple>.Context context) throws IOException, InterruptedException {
	
	}
}
