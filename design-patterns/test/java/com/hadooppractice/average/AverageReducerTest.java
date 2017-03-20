package com.hadooppractice.average;

import com.hadooppractice.testutils.MockitoExtension;
import com.hadooppractice.testutils.SampleData;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AverageReducerTest {

    @Test
    void reduceShouldCalculateAverage(@Mock Reducer<Text, AverageTuple, Text, AverageTuple>.Context mockContext) throws IOException, InterruptedException {
        AverageTuple expectedAverage = new AverageTuple(15.0, 12L);

        Iterable<AverageTuple> values = Arrays.asList(
                new AverageTuple(10.0, 1L),
                new AverageTuple(10.0, 2L),
                new AverageTuple(10.0, 3L),
                new AverageTuple(20.0, 6L));

        Text key = new Text(SampleData.COMMENT_ROW_USER_ID);

        AverageReducer reducer = new AverageReducer();
        reducer.reduce(key, values, mockContext);

        verify(mockContext).write(key, expectedAverage);
    }
}
