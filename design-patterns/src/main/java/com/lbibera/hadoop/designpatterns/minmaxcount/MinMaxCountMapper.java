package com.lbibera.hadoop.designpatterns.minmaxcount;

import com.lbibera.hadoop.designpatterns.utils.DateConverter;
import com.lbibera.hadoop.designpatterns.utils.XMLParser;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class MinMaxCountMapper extends Mapper<Object, Text, Text, MinMaxCountTuple> {

	private static final String TAG_ID = "UserId";
	private static final String TAG_MIN_DATE = "CreationDate";
	private static final String TAG_MAX_DATE = "CreationDate";

	private Text keyOut = new Text();
	private MinMaxCountTuple tuple = new MinMaxCountTuple();

	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, MinMaxCountTuple>.Context context) throws IOException, InterruptedException {
		Map<String, String> row = XMLParser.toMap(value.toString());
		
		if (!isValid(row)) return;
		
		keyOut.set(row.get(TAG_ID));
		tuple.setCount(1L);
		tuple.setMax(DateConverter.stringToDate(row.get(TAG_MAX_DATE)));
		tuple.setMin(DateConverter.stringToDate(row.get(TAG_MIN_DATE)));

		context.write(keyOut, tuple);
	}

	private boolean isValid(Map<String, String> row) {
		return !row.getOrDefault(TAG_ID, "").isEmpty()
				&& !row.getOrDefault(TAG_MIN_DATE, "").isEmpty()
				&& !row.getOrDefault(TAG_MAX_DATE, "").isEmpty();
	}
}
