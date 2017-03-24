package com.lbibera.hadoop.designpatterns.medianstd;

import java.io.IOException;
import java.util.Map;

import com.lbibera.hadoop.designpatterns.utils.DateConverter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.lbibera.hadoop.designpatterns.utils.XMLParser;

public class MedianStdMapper extends Mapper<Object, Text, IntWritable, IntWritable> {

	private static final String FIELD_DATE = "CreationDate";
	private static final String FIELD_COMMENT = "Text";

	private IntWritable outKey = new IntWritable(0);
	private IntWritable outValue = new IntWritable(0);

	@Override
	protected void map(Object dontUseMe, Text value, Mapper<Object, Text, IntWritable, IntWritable>.Context context) throws IOException, InterruptedException {
		Map<String, String> parsed = XMLParser.toMap(value);

		if (!isValidRow(parsed)) return;

		outKey.set(DateConverter.stringToDate(parsed.get(FIELD_DATE)).getHour());
		outValue.set(parsed.get(FIELD_COMMENT).length());

		context.write(outKey, outValue);
	}

	private boolean isValidRow(Map<String, String> parsedRow) {
		return parsedRow.get(FIELD_DATE) != null
				&& !parsedRow.get(FIELD_DATE).isEmpty()
				&& parsedRow.get(FIELD_COMMENT) != null
				&& !parsedRow.get(FIELD_COMMENT).isEmpty();
	}
}
