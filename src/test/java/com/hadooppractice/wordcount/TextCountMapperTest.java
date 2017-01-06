package com.hadooppractice.wordcount;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.hadooppractice.testutils.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TextCountMapperTest {
	
	@BeforeEach
    void init(@Mock Mapper<Object, Text, Text, LongWritable>.Context mockContext) throws IOException, InterruptedException {
		doNothing().when(mockContext).write(any(), any());
    }

	@Test
	void mapShouldRunSuccessfuly(@Mock Mapper<Object, Text, Text, LongWritable>.Context mockContext) throws IOException, InterruptedException {
		
		TextCountMapper mapper = new TextCountMapper();
		
		Text value = new Text("test1 test2 test3");
		mapper.map(null, value, mockContext);
		
		verify(mockContext, times(1)).write(new Text("test1"), new LongWritable(1));
		verify(mockContext, times(1)).write(new Text("test2"), new LongWritable(1));
		verify(mockContext, times(1)).write(new Text("test3"), new LongWritable(1));
	}
}
