package com.hadooppractice.medianstd;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MedianStdTuple implements Writable {
	
	private Double median;
	private Double standardDeviation;

	@Override
	public void readFields(DataInput input) throws IOException {
		median = input.readDouble();
		standardDeviation = input.readDouble();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeDouble(median);
		output.writeDouble(standardDeviation);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((median == null) ? 0 : median.hashCode());
		result = prime * result + ((standardDeviation == null) ? 0 : standardDeviation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedianStdTuple other = (MedianStdTuple) obj;
		if (median == null) {
			if (other.median != null)
				return false;
		} else if (!median.equals(other.median))
			return false;
		if (standardDeviation == null) {
			if (other.standardDeviation != null)
				return false;
		} else if (!standardDeviation.equals(other.standardDeviation))
			return false;
		return true;
	}

	public Double getMedian() {
		return median;
	}

	public void setMedian(Double median) {
		this.median = median;
	}

	public Double getStandardDeviation() {
		return standardDeviation;
	}

	public void setStandardDeviation(Double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	@Override
	public String toString() {
		return String.format("%s %s", median, standardDeviation);
	}

}
