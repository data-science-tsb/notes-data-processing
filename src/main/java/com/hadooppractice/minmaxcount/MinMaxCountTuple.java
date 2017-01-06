package com.hadooppractice.minmaxcount;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.hadoop.io.Writable;

public class MinMaxCountTuple implements Writable {
	
//	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSS");
	
	private LocalDateTime min;
	private LocalDateTime max;
	private Long count;

	@Override
	public void readFields(DataInput input) throws IOException {
		min = LocalDateTime.ofInstant(Instant.ofEpochMilli(input.readLong()), ZoneId.systemDefault());
		max = LocalDateTime.ofInstant(Instant.ofEpochMilli(input.readLong()), ZoneId.systemDefault());
		count = input.readLong();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeLong(min.atZone(ZoneId.systemDefault()).toEpochSecond());
		output.writeLong(max.atZone(ZoneId.systemDefault()).toEpochSecond());
		output.writeLong(count);
	}

	public LocalDateTime getMin() {
		return min;
	}

	public void setMin(LocalDateTime min) {
		this.min = min;
	}

	public LocalDateTime getMax() {
		return max;
	}

	public void setMax(LocalDateTime max) {
		this.max = max;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
