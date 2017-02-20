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

public class OptimizedMedianStdReducer extends Reducer<IntWritable, SortedMapWritable<IntWritable>, IntWritable, MedianStdTuple> {

    @Override
    protected void reduce(IntWritable key, Iterable<SortedMapWritable<IntWritable>> values, Reducer<IntWritable, SortedMapWritable<IntWritable>, IntWritable, MedianStdTuple>.Context context) throws IOException, InterruptedException {

        SortedMapWritable<IntWritable> combinedValues = StreamSupport
                .stream(values.spliterator(), false)
                .reduce(new SortedMapWritable<>(), SortedMapWritableUtil::combineMap);

        //find the median
        int size = computeSize(combinedValues);
        double median = computeMedian(combinedValues, size);

        //find the mean
        long sum = combinedValues.entrySet().stream().mapToLong((e) -> {
                    long val = e.getKey().get();
                    long count = ((IntWritable)e.getValue()).get();
                    return val * count;
                })
                .sum();
        double mean = sum / size;

        //find the std dev
        double sumOfStd = combinedValues.entrySet().stream()
                .map(n -> Math.pow((n.getKey()).get()-mean, 2) * 1.0 * ((IntWritable)n.getValue()).get())
                .reduce(0.0, Double::sum) / size;

        double std = Math.sqrt(sumOfStd);

        MedianStdTuple outValue = new MedianStdTuple();
        outValue.setMedian(median);
        outValue.setStandardDeviation(std);

        context.write(key, outValue);
    }

    /**
     * computes the size of the compacted bag of values
     * @param values
     * @return
     */
    protected int computeSize(SortedMapWritable<IntWritable> values) {
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
    protected double computeMedian(SortedMapWritable<IntWritable> values, int size) {

        boolean odd = size % 2 == 1;
        int mid = (size / 2) + 1;
        int currentIndex = 0;
        int previousEntry = 0;

        for (Map.Entry<IntWritable, Writable> entry : values.entrySet()) {
            int currentSize = ((IntWritable)entry.getValue()).get();
            currentIndex += currentSize;

            boolean passMid = currentIndex >= mid;
            if (passMid && odd) {
                return  entry.getKey().get();
            } else if (passMid && (currentIndex-currentSize < mid-1)) {
                return entry.getKey().get();
            } else if (passMid) {
                return (previousEntry + entry.getKey().get()) / 2.0;
            }

            previousEntry = entry.getKey().get();
        }

        return 0;
    }
}
