package com.hadooppractice.firstlettercounter;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hadooppractice.utils.XMLParser;

public class FirstLetterMapper extends Mapper<Object, Text, Text, LongWritable> {
	
	private static final String TAG_FIELD = "TagName";
	
	private Text letter = new Text("");
	private final LongWritable count = new LongWritable(1);

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		Map<String, String> parsedXml = XMLParser.toMap(value.toString());
		String word = parsedXml.get(TAG_FIELD);
		
		if (word != null && !word.trim().isEmpty()) {
			letter.set(word.trim().substring(0, 1));
			context.write(letter, count);
		} else {
			letter.set("ERROR");
			context.write(letter, count);
		}
	}
}
