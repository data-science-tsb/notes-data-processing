package com.lbibera.hadoop.designpatterns.wordcount;

import static org.mockito.Mockito.*;

import com.lbibera.hadoop.designpatterns.testutils.MockitoExtension;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TextCountMapperTest {
	
	@Test
	void mapShouldRunSuccessfully(@Mock Mapper<Object, Text, Text, LongWritable>.Context mockContext) throws IOException, InterruptedException {
		TextCountMapper mapper = new TextCountMapper();
		
		Text value = new Text("test1 test2 test3 test3");
		mapper.map(null, value, mockContext);

        verify(mockContext).write(new Text("test1"), new LongWritable(1));
        verify(mockContext).write(new Text("test2"), new LongWritable(1));
        verify(mockContext, times(2)).write(new Text("test3"), new LongWritable(1));
	}

}
