package com.hadooppractice.minmaxcount;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.hadooppractice.utils.XMLParser;

public class MinMaxCountMapper extends Mapper<Object, Text, Text, MinMaxCountTuple> {

	private static final String TAG_ID = "Id";
	private static final String TAG_MIN_DATE = "ExcerptPostId";
	private static final String TAG_MAX_DATE = "WikiPostId";

	private Text keyOut = new Text();
	private MinMaxCountTuple tuple = new MinMaxCountTuple();

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, MinMaxCountTuple>.Context context) throws IOException, InterruptedException {
		Map<String, String> row = XMLParser.toMap(value.toString());
		
		if (!isValid(row)) return;
		
		tuple.setCount(1L);
		tuple.setMax(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(row.get(TAG_MAX_DATE))), ZoneId.systemDefault()));
		tuple.setMin(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(row.get(TAG_MIN_DATE))), ZoneId.systemDefault()));
		keyOut.set(row.get(TAG_ID).substring(0, 1));
		context.write(keyOut, tuple);
	}

	private boolean isValid(Map<String, String> row) {
		return !row.getOrDefault(TAG_ID, "").isEmpty()
				&& !row.getOrDefault(TAG_MIN_DATE, "").isEmpty()
				&& !row.getOrDefault(TAG_MAX_DATE, "").isEmpty();
	}
}
