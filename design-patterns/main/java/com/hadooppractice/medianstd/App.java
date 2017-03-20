package com.hadooppractice.medianstd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration config = new Configuration();
		
		Job job = Job.getInstance(config);
		job.setJobName("median-standard-deviation");
		
		job.setJarByClass(App.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(MedianStdMapper.class);
		job.setReducerClass(MedianStdReducer.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}

}
