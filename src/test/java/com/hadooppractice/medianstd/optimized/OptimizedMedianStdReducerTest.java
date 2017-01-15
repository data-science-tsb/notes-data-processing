package com.hadooppractice.medianstd.optimized;

import com.hadooppractice.medianstd.MedianStdTuple;
import com.hadooppractice.testutils.MockitoExtension;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OptimizedMedianStdReducerTest extends  OptimizedMedianStdReducer {

    @Test
    void shouldCalculateStandardDeviationAndMedian(@Mock Reducer<IntWritable, SortedMapWritable, IntWritable, MedianStdTuple>.Context mockContext) throws IOException, InterruptedException {
        IntWritable key = new IntWritable(1);
        List<SortedMapWritable> values = Stream
                .of(
                        Pair.of(9, 4),
                        Pair.of(2, 1),
                        Pair.of(4, 3),
                        Pair.of(4, 1),
                        Pair.of(5, 2),
                        Pair.of(12, 2),
                        Pair.of(7, 2),
                        Pair.of(8, 1),
                        Pair.of(11, 1),
                        Pair.of(3, 1),
                        Pair.of(10, 1),
                        Pair.of(6, 1)
                )
                .map(n -> {
                    SortedMapWritable sortedMapWritable = new SortedMapWritable();
                    sortedMapWritable.put(new IntWritable(n.getKey()), new IntWritable(n.getValue()));
                    return sortedMapWritable;
                })
                .collect(Collectors.toList());

        Double expectedMedian = 7.0;
        Double expectedStd = 2.983;

        OptimizedMedianStdReducer reducer = new OptimizedMedianStdReducer();
        reducer.reduce(key, values, mockContext);

        ArgumentCaptor<MedianStdTuple> captor = ArgumentCaptor.forClass(MedianStdTuple.class);
        verify(mockContext).write(eq(key), captor.capture());

        MedianStdTuple actualValue = captor.getValue();
        assertEquals(expectedStd, actualValue.getStandardDeviation(), 0.001);
        assertEquals(expectedMedian, actualValue.getMedian(), 0.001);
    }

    @Test
    void shouldComputeMedianForEvenValues() {

        //1 2 2 3 10 11 11 12 12 12
        SortedMapWritable values = new SortedMapWritable();
        values.put(new IntWritable(1), new IntWritable(1));
        values.put(new IntWritable(2), new IntWritable(2));
        values.put(new IntWritable(3), new IntWritable(1));
        values.put(new IntWritable(10), new IntWritable(1));
        values.put(new IntWritable(11), new IntWritable(2));
        values.put(new IntWritable(12), new IntWritable(3));

        double expectedMedian = 10.5;

        OptimizedMedianStdReducer reducer = new OptimizedMedianStdReducer();
        double actualMedian = reducer.computeMedian(values, 10);

        assertEquals(expectedMedian, actualMedian);
    }

    @Test
    void shouldComputeMedianForOddValues() {

        //1 2 2 3 10 11 11 12 12
        SortedMapWritable values = new SortedMapWritable();
        values.put(new IntWritable(1), new IntWritable(1));
        values.put(new IntWritable(2), new IntWritable(2));
        values.put(new IntWritable(3), new IntWritable(1));
        values.put(new IntWritable(10), new IntWritable(1));
        values.put(new IntWritable(11), new IntWritable(2));
        values.put(new IntWritable(12), new IntWritable(2));

        double expectedMedian = 10;

        OptimizedMedianStdReducer reducer = new OptimizedMedianStdReducer();
        double actualMedian = reducer.computeMedian(values, 9);

        assertEquals(expectedMedian, actualMedian);
    }
}
