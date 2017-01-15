package com.hadooppractice.medianstd.optimized;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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

@ExtendWith(MockitoExtension.class)
public class OptimizedMedianStdCombinerTest {

    @Test
    void shouldCombineMaps(@Mock Reducer<IntWritable, SortedMapWritable, IntWritable, SortedMapWritable>.Context mockContext) throws IOException, InterruptedException {

        IntWritable key = new IntWritable(1);

        List<SortedMapWritable> values = Stream.of(
                Pair.of(1, 1),
                Pair.of(1, 3),
                Pair.of(2, 7),
                Pair.of(2, 5))
                .map(v -> {
                    SortedMapWritable map = new SortedMapWritable();
                    map.put(new IntWritable(v.getKey()), new IntWritable(v.getValue()));
                    return  map;
                })
                .collect(Collectors.toList());

        OptimizedMedianStdCombiner combiner = new OptimizedMedianStdCombiner();
        combiner.reduce(key, values, mockContext);

        ArgumentCaptor<SortedMapWritable> captor = ArgumentCaptor.forClass(SortedMapWritable.class);

        verify(mockContext).write(eq(key), captor.capture());

        SortedMapWritable combineMap = captor.getValue();
        assertEquals(combineMap.get(new IntWritable(1)), new IntWritable(4));
        assertEquals(combineMap.get(new IntWritable(2)), new IntWritable(12));
    }
}
