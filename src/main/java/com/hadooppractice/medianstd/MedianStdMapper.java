package com.hadooppractice.medianstd;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MedianStdMapper extends Mapper<Object, Text, Text, MedianStdTuple> {

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, MedianStdTuple>.Context context) throws IOException, InterruptedException {
		
	}
}
