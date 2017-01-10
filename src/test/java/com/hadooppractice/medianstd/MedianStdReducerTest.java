package com.hadooppractice.medianstd;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import com.hadooppractice.testutils.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedianStdReducerTest {
	
	@Test
	void shouldCalculateStandardDeviationAndMedian(@Mock Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>.Context mockContext) throws IOException, InterruptedException {
		IntWritable key = new IntWritable(1);
		List<IntWritable> values = Arrays.asList(
				new IntWritable(10), 
				new IntWritable(12), 
				new IntWritable(13), 
				new IntWritable(11), 
				new IntWritable(11), 
				new IntWritable(9));
		
		//1 + 1 + 4 + 0 + 0 + 4 = 10
		//3.16227
		Double expectedMedian = 11.0;
		Double expectedStd = 3.16227;
		
		ArgumentCaptor<MedianStdTuple> captor = ArgumentCaptor.forClass(MedianStdTuple.class);
		
		MedianStdReducer reducer = new MedianStdReducer();
		reducer.reduce(key, values, mockContext);
		
		verify(mockContext).write(eq(key), captor.capture());

		MedianStdTuple actualValue = captor.getValue();
		assertEquals(expectedStd, actualValue.getStandardDeviation(), 0.0001);
		assertEquals(expectedMedian, actualValue.getMedian(), 0.0001);
	}
}
