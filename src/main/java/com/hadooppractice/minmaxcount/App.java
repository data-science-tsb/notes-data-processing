package com.hadooppractice.minmaxcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf, "min-max-count");

		job.setJarByClass(App.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(MinMaxCountTuple.class);

		job.setMapperClass(MinMaxCountMapper.class);
		job.setCombinerClass(MinMaxCountReducer.class);
		job.setReducerClass(MinMaxCountReducer.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
