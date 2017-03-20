package com.hadooppractice.utils;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SortedMapWritable;
import org.apache.hadoop.io.Writable;

public class SortedMapWritableUtil {

    /**
     * really amazing function, performs add on individual values
     * @param a
     * @param b
     * @return
     */
    public static SortedMapWritable combineMap(SortedMapWritable a, SortedMapWritable b) {
        SortedMapWritable c = new SortedMapWritable(a);

        b.forEach((k, v) -> {
            if (c.get(k) == null) {
                c.put(k, v);
            } else {
                Writable valA = a.get(k);
                Writable valB = b.get(k);

                c.put(k, add(valA, valB));
            }
        });

        return c;
    }

    private static Writable add(Writable valA, Writable valB) {
        if (valA instanceof  LongWritable && valB instanceof LongWritable) {
            return add((LongWritable)valA, (LongWritable)valB);
        } else if (valA instanceof IntWritable && valB instanceof IntWritable) {
            return add((IntWritable)valA, (IntWritable)valB);
        }
        return valA;
    }

    private static LongWritable add(LongWritable a, LongWritable b) {
        return new LongWritable(a.get() + b.get());
    }

    private static IntWritable add(IntWritable a, IntWritable b) {
        return new IntWritable(a.get() + b.get());
    }
}
