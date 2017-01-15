package com.hadooppractice.utils;

import org.apache.hadoop.io.SortedMapWritable;

/**
 * Created by lbibera on 15/01/2017.
 */
public class SortedMapWritableUtil {

    public static interface EntryCombiner<V> {
        V combine(V a, V b);
    }

    public static SortedMapWritable combineMap(SortedMapWritable a, SortedMapWritable b) {

    }
}
