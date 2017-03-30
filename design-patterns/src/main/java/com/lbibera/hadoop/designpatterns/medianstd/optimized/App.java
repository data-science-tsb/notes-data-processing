package com.lbibera.hadoop.designpatterns.medianstd.optimized;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/***
 * this is a memory-optimized version of the median-std calculator,
 * we are banking on low cardinality of comment lengths
 */
public class App {

    public static void main(String... args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration config = new Configuration();

        Job job = Job.getInstance(config);

        job.setJobName("optimized-median-std");
        job.setJarByClass(App.class);
        job.setMapperClass(OptimizedMedianStdMapper.class);
        job.setCombinerClass(OptimizedMedianStdCombiner.class);
        job.setReducerClass(OptimizedMedianStdReducer.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.waitForCompletion(true);
    }
}
