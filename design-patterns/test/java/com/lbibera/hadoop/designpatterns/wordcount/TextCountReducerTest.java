package com.lbibera.hadoop.designpatterns.wordcount;

import com.lbibera.hadoop.designpatterns.testutils.MockitoExtension;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TextCountReducerTest {

    @Test
    void shouldAggregateWordCount(@Mock Reducer<Text, LongWritable, Text, LongWritable>.Context mockContext) throws IOException, InterruptedException {

        Text key = new Text("someWord");
        List<LongWritable> values = Stream.of(1, 2, 3, 4, 5)
                .map(LongWritable::new)
                .collect(Collectors.toList());

        TextCountReducer reducer = new TextCountReducer();
        reducer.reduce(key, values, mockContext);

        verify(mockContext).write(eq(key), eq(new LongWritable(15)));
    }
}
