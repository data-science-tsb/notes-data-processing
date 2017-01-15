package com.hadooppractice.sampler;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SamplerReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
	
		int i = 0;
		for (Text t : values) {
			context.write(new Text(key.toString() + "_" + i++), t);
		}
	}
}
