package com.lbibera.hadoop.designpatterns.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TextCountMapper extends Mapper<Object, Text, Text, LongWritable> {
	private final static LongWritable ONE = new LongWritable(1);

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		StringTokenizer tokenizer = new StringTokenizer(value.toString());

		while (tokenizer.hasMoreTokens()) {
			context.write(new Text(tokenizer.nextToken()), ONE);
		}
	}
}
