package com.lbibera.hadoop.designpatterns.medianstd;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class MedianStdReducer extends Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>{

	//shit version: wont hold out for large data
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values, Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>.Context context) throws IOException, InterruptedException {
		List<Integer> sortedData = StreamSupport
				.stream(values.spliterator(), false)
				.map(IntWritable::get)
				.collect(Collectors.toList());
		
		Collections.sort(sortedData);

		//calculate median
		Double median;
		int mid = sortedData.size() / 2;
		if (sortedData.size()%2 == 2) {
			median = (double) (sortedData.get(mid) + sortedData.get(mid + 1))/2;
		} else {
			median = (double) sortedData.get(mid);
		}

		//calculate standard deviation
		//https://www.mathsisfun.com/data/standard-deviation-formulas.html
		Double mean = sortedData.stream()
				.mapToDouble(Integer::doubleValue)
				.sum() / sortedData.size();
		
		Double meanOfDeviation = sortedData.stream()
				.map(n -> Math.pow(n-mean, 2))
				.reduce(0.0, Double::sum) / sortedData.size();
		
		Double std = Math.sqrt(meanOfDeviation);

        MedianStdTuple tuple = new MedianStdTuple();
        tuple.setMedian(median);
        tuple.setStandardDeviation(std);

		context.write(key, tuple);
	}
}
