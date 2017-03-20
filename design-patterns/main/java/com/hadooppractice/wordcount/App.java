package com.hadooppractice.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class App extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new App(), args);
        System.exit(res);
	}

    @Override
    public int run(String[] args) throws Exception {
        Configuration conf = getConf();

        Job job = Job.getInstance(conf, "word-count");

        job.setJarByClass(App.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapperClass(TextCountMapper.class);
        job.setReducerClass(TextCountReducer.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return job.waitForCompletion(true) ? 0 : 1;
    }
}
