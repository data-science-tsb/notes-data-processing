package com.lbibera.hadoop.designpatterns.medianstd;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import com.lbibera.hadoop.designpatterns.testutils.MockitoExtension;
import com.lbibera.hadoop.designpatterns.testutils.SampleData;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
public class MedianStdMapperTest {

	@Test
	void shouldMapCorrectly(@Mock Mapper<Object, Text, IntWritable, IntWritable>.Context mockContext) throws IOException, InterruptedException {
		IntWritable expectedTime = new IntWritable(8);
		IntWritable expectedLength = new IntWritable(73);
		
		MedianStdMapper mapper = new MedianStdMapper();
		mapper.map(null, new Text(SampleData.COMMENT_ROW), mockContext);
		
		verify(mockContext).write(expectedTime, expectedLength);
	}

	@Test
	void shouldHandleIncorrectFields(@Mock Mapper<Object, Text, IntWritable, IntWritable>.Context mockContext) throws Exception {
		IntWritable expectedTime = new IntWritable(8);
		IntWritable expectedLength = new IntWritable(73);

		MedianStdMapper mapper = new MedianStdMapper();
		mapper.map(null, new Text("<row>"), mockContext);

		verify(mockContext, never()).write(expectedTime, expectedLength);
	}
}
