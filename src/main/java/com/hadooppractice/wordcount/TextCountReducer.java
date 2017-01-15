package com.hadooppractice.wordcount;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TextCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	public void reduce(Text key, Iterable<LongWritable> values, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		Long sum = StreamSupport
				.stream(values.spliterator(), false)
				.mapToLong(LongWritable::get).sum();

		context.write(key, new LongWritable(sum));
	}
}
