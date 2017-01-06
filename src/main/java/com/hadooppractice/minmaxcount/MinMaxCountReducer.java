package com.hadooppractice.minmaxcount;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MinMaxCountReducer extends Reducer<Text, MinMaxCountTuple, Text, MinMaxCountTuple> {

	@Override
	protected void reduce(Text text, Iterable<MinMaxCountTuple> values, Reducer<Text, MinMaxCountTuple, Text, MinMaxCountTuple>.Context context) throws IOException, InterruptedException {
		MinMaxCountTuple out = new MinMaxCountTuple();
		
//		values.forEach(v -> {
//			if (v.getMax().isAfter(out.getMax())) {
//				out.setMax(v.getMax());
//			}
//			if (v.getMin().isBefore(out.getMin())) {
//				out.setMin(v.getMin());
//			}
//			out.setCount(out.getCount() + v.getCount());
//		});
		
		for (MinMaxCountTuple v : values) {
			if (v.getMax().isAfter(out.getMax())) {
				out.setMax(v.getMax());
			}
			if (v.getMin().isBefore(out.getMin())) {
				out.setMin(v.getMin());
			}
			out.setCount(out.getCount() + v.getCount());
		}
		
		context.write(text, out);
	}
}
