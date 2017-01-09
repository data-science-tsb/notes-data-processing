package com.hadooppractice.medianstd;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianStdReducer extends Reducer<Text, MedianStdTuple, Text, MedianStdTuple>{

	@Override
	protected void reduce(Text arg0, Iterable<MedianStdTuple> arg1, Reducer<Text, MedianStdTuple, Text, MedianStdTuple>.Context arg2) throws IOException, InterruptedException {
	
	}
}
