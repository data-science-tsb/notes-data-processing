package com.hadooppractice.medianstd;

import com.hadooppractice.testutils.MockitoExtension;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MedianStdReducerTest {
	
	@Test
	void shouldCalculateStandardDeviationAndMedian(@Mock Reducer<IntWritable, IntWritable, IntWritable, MedianStdTuple>.Context mockContext) throws IOException, InterruptedException {
		IntWritable key = new IntWritable(1);
		List<IntWritable> values = Stream
				.of(9, 2, 5, 4, 12, 7, 8, 11, 9, 3, 7, 4, 12, 5, 4, 10, 9, 6, 9, 4)
				.map(n -> new IntWritable(n))
				.collect(Collectors.toList());

		Double expectedMedian = 7.0;
		Double expectedStd = 2.983;

		MedianStdReducer reducer = new MedianStdReducer();
		reducer.reduce(key, values, mockContext);

		ArgumentCaptor<MedianStdTuple> captor = ArgumentCaptor.forClass(MedianStdTuple.class);
		verify(mockContext).write(eq(key), captor.capture());

        MedianStdTuple actualValue = captor.getValue();
		assertEquals(expectedStd, actualValue.getStandardDeviation(), 0.001);
		assertEquals(expectedMedian, actualValue.getMedian(), 0.001);
	}
}
