package com.hadooppractice.sampler;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SamplerMapper extends Mapper<Object, Text, Text, Text> {
	
	public static final double CHANCE = .01;

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		if (Math.random() < CHANCE) {
			context.write(new Text(System.currentTimeMillis()+""), value);
		}
	}
}
