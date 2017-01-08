package com.hadooppractice.minmaxcount;

import static org.mockito.Mockito.*;

import com.hadooppractice.testutils.MockitoExtension;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class MinMaxCountReducerTest {

    @Test
    void shouldReduceCorrectly(@Mock Reducer<Text, MinMaxCountTuple, Text, MinMaxCountTuple>.Context context) throws IOException, InterruptedException {

        Text key = new Text("100001");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        LocalDateTime yesterday = now.minusDays(1);

        Iterable<MinMaxCountTuple> values = Arrays.asList(
                new MinMaxCountTuple(now, tomorrow, 1L),
                new MinMaxCountTuple(yesterday, now, 1L),
                new MinMaxCountTuple(now, now, 2L));

        MinMaxCountTuple expected = new MinMaxCountTuple(yesterday, tomorrow, 4L);

        MinMaxCountReducer reducer = new MinMaxCountReducer();
        reducer.reduce(key, values, context);

        verify(context).write(key, expected);
    }
}
