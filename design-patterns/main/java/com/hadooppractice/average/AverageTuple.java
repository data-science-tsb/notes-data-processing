package com.hadooppractice.average;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by lbibera on 08/01/2017.
 */
public class AverageTuple implements Writable {

    private Double averageLength = 0.0;
    private Long count = 0L;

    public AverageTuple(Double averageLength, Long count) {
        this.averageLength = averageLength;
        this.count = count;
    }

    public AverageTuple() {}

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeDouble(averageLength);
        dataOutput.writeLong(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        averageLength = dataInput.readDouble();
        count = dataInput.readLong();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AverageTuple)) return false;

        AverageTuple that = (AverageTuple) o;

        if (averageLength != null ? !averageLength.equals(that.averageLength) : that.averageLength != null)
            return false;
        return count != null ? count.equals(that.count) : that.count == null;
    }

    @Override
    public int hashCode() {
        int result = averageLength != null ? averageLength.hashCode() : 0;
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }

    public Double getAverageLength() {
        return averageLength;
    }

    public void setAverageLength(Double averageLength) {
        this.averageLength = averageLength;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AverageTuple{" +
                "averageLength=" + averageLength +
                ", count=" + count +
                '}';
    }
}
