package com.lbibera.hadoop.designpatterns.firstlettercounter;

import java.io.IOException;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FirstLetterReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	private LongWritable value = new LongWritable(0);
	
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values, Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		
		Long sum = StreamSupport
				.stream(values.spliterator(), true)
				.map(v -> v.get())
				.reduce(0L, (a, b) -> a + b);
		
		value.set(sum);
		context.write(key, value);
	}
}
