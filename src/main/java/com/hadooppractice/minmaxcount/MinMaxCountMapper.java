package com.hadooppractice.minmaxcount;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MinMaxCountMapper extends Mapper<Object, Text, Text, MinMaxCountTuple> {

	/**
	 * value: <user-id>,<post-id>,<comment-id>,<comment-date>,<comment-string>
	 * example: 1,2,1,1483610209143,this is a comment
	 */
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, MinMaxCountTuple>.Context context) throws IOException, InterruptedException {
		
//		String[] tokens = value.toString().split(",");
		
	}
}
