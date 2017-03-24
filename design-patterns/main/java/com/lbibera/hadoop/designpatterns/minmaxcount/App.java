package com.lbibera.hadoop.designpatterns.minmaxcount;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Problem:
 * Given a list of user's comments, determine the first and last time a user has commented
 * and the total number of comments from that user.
 *
 * sample data:
 *
 * 	<row
 * 	Id="2579740"
 * 	PostId="2573882"
 * 	Text="Are you getting any results? What are you specifying as the command text?"
 * 	CreationDate="2010-04-04T08:48:51.347"
 * 	UserId="95437"
 * 	/>
 */
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
