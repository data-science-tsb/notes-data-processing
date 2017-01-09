package com.hadooppractice.medianstd;

import static com.hadooppractice.testutils.SampleData.COMMENT_ROW;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.hadooppractice.testutils.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedianStdMapperTest {

	@Test
	void shouldMapCorrectly(@Mock Mapper<Object, Text, Text, IntWritable>.Context mockContext) throws IOException, InterruptedException {
		Text expectedTime = new Text("08");
		IntWritable expectedLength = new IntWritable(63);
		
		MedianStdMapper mapper = new MedianStdMapper();
		mapper.map(null, new Text(COMMENT_ROW), mockContext);
		
		verify(mockContext).write(expectedTime, expectedLength);
	}
}
