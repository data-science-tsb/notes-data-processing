package com.hadooppractice.medianstd.optimized;

import com.hadooppractice.medianstd.MedianStdTuple;
import com.hadooppractice.utils.SortedMapWritableUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Map;
import java.util.stream.StreamSupport;

public class OptimizedMedianStdReducer extends Reducer<IntWritable, SortedMapWritable, IntWritable, MedianStdTuple> {

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable> values, Reducer<IntWritable, SortedMapWritable, IntWritable, MedianStdTuple>.Context context) throws IOException, InterruptedException {

        SortedMapWritable combinedValues = StreamSupport
                .stream(values.spliterator(), false)
                .reduce(new SortedMapWritable(), SortedMapWritableUtil::combineMap);

        //find the median
        int size = computeSize(combinedValues);
        double median = computeMedian(combinedValues, size);

        //find the mean

        //find the
    }

    /**
     * computes the size of the compacted bag of values
     * @param values
     * @return
     */
    protected int computeSize(SortedMapWritable values) {
        return values.entrySet()
                .stream()
                .mapToInt((e) -> {
                    if (e.getKey() instanceof IntWritable) {
                        return  ((IntWritable) e.getValue()).get();
                    }
                    return 0;
                })
                .sum();
    }

    /**
     * computes the middle value pf the sorted values.
     * if the size is odd, get the middle value, otherwise
     * calculate the mean of the two middle values
     * @param values
     * @param size
     * @return
     */
    protected double computeMedian(SortedMapWritable values, int size) {

        boolean even = size % 2 == 0;
        int mid = even ? size / 2 + 1 : size / 2 + 1;
        int currentIndex = 0;
        int previousEntry = 0;

        for (Map.Entry<WritableComparable, Writable> entry : values.entrySet()) {
            int currentSize = ((IntWritable)entry.getValue()).get();
            currentIndex += currentSize;

            if (currentIndex >= mid && !even) {
                return ((IntWritable)entry.getKey()).get();
            } else if (currentIndex >= mid && even) {
                return (previousEntry + ((IntWritable)entry.getKey()).get()) / 2.0;
            }

            previousEntry = ((IntWritable)entry.getKey()).get();
        }

        return 0;
    }
}
