package com.hadooppractice.medianstd.optimized;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

/**
 * Created by lbibera on 14/01/2017.
 */
public class App {

    public static void main(String... args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration config = new Configuration();

        Job job = Job.getInstance(config);

        job.setJobName("optimized-median-std");
        job.setMapperClass(OptimizedMedianStdMapper.class);
        job.setCombinerClass(OptimizedMedianStdCombiner.class);

        job.waitForCompletion(true);
    }
}
