package com.hadooppractice.minmaxcount;

import static com.hadooppractice.testutils.SampleData.COMMENT_ROW;
import static com.hadooppractice.testutils.SampleData.COMMENT_ROW_USER_ID;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.hadooppractice.testutils.MockitoExtension;
import com.hadooppractice.testutils.SampleData;
import com.hadooppractice.utils.DateConverter;

@ExtendWith(MockitoExtension.class)
public class MinMaxCountMapperTest {

    @Test
    void shouldMapCorrectly(@Mock Mapper<Object, Text, Text, MinMaxCountTuple>.Context mockContext) throws IOException, InterruptedException {
        MinMaxCountTuple expectedMappedTuple = new MinMaxCountTuple();
        expectedMappedTuple.setCount(1L);
        expectedMappedTuple.setMax(DateConverter.stringToDate(SampleData.COMMENT_ROW_CREATION_DATE));
        expectedMappedTuple.setMin(DateConverter.stringToDate(SampleData.COMMENT_ROW_CREATION_DATE));

        MinMaxCountMapper mapper = new MinMaxCountMapper();
        mapper.map(null, new Text(COMMENT_ROW), mockContext);

        verify(mockContext).write(new Text(COMMENT_ROW_USER_ID), expectedMappedTuple);
    }
}
