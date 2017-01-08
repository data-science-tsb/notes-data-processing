package com.hadooppractice.minmaxcount;

import com.hadooppractice.utils.DateConverter;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.time.LocalDateTime;

public class MinMaxCountTuple implements Writable {
	
	private LocalDateTime min = LocalDateTime.MAX;
	private LocalDateTime max = LocalDateTime.MIN;
	private Long count = 0L;

	@Override
	public void readFields(DataInput input) throws IOException {
		min = DateConverter.stringToDate(input.readUTF());
		max = DateConverter.stringToDate(input.readUTF());
		count = input.readLong();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeUTF(DateConverter.dateToString(min));
		output.writeUTF(DateConverter.dateToString(max));
		output.writeLong(count);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof MinMaxCountTuple)) return false;

		MinMaxCountTuple that = (MinMaxCountTuple) o;

		if (getMin() != null ? !getMin().equals(that.getMin()) : that.getMin() != null) return false;
		if (getMax() != null ? !getMax().equals(that.getMax()) : that.getMax() != null) return false;
		return getCount() != null ? getCount().equals(that.getCount()) : that.getCount() == null;
	}

	@Override
	public int hashCode() {
		int result = getMin() != null ? getMin().hashCode() : 0;
		result = 31 * result + (getMax() != null ? getMax().hashCode() : 0);
		result = 31 * result + (getCount() != null ? getCount().hashCode() : 0);
		return result;
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

	@Override
	public String toString() {
		return String.format("%s %s %s", DateConverter.dateToString(getMin()), DateConverter.dateToString(getMax()), getCount());
	}

}
