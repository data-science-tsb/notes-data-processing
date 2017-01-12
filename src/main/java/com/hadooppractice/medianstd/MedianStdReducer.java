package com.hadooppractice.medianstd;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianStdReducer extends Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>{

	//shit version: wont hold out for large data
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values, Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>.Context context) throws IOException, InterruptedException {
		MedianStdTuple outValue = new MedianStdTuple();

		List<Integer> sortedData = new LinkedList<>();
		values.forEach(val -> {
			sortedData.add(val.get());
		});
		Collections.sort(sortedData);

		Double median = 0.0;
		int mid = sortedData.size() / 2;
		if (sortedData.size()%2 == 2) {
			median = (double) (sortedData.get(mid) + sortedData.get(mid + 1))/2;
		} else {
			median = (double) sortedData.get(mid);
		}

		outValue.setMedian(median);
		outValue.setStandardDeviation(0.00); //TODO: compute std
		context.write(key, outValue);
	}
}
