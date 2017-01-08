package com.hadooppractice.average;

import static org.mockito.Mockito.*;

import com.hadooppractice.testutils.MockitoExtension;
import com.hadooppractice.testutils.SampleData;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class AverageMapperTest {

    @Test
    void mapperShouldWork(@Mock Mapper<Object, Text, Text, AverageTuple>.Context mockContext ) throws IOException, InterruptedException {
        Text expectedUserId = new Text(SampleData.COMMENT_ROW_USER_ID);
        AverageTuple expectedAverage = new AverageTuple();
        expectedAverage.setCount(1L);
        expectedAverage.setAverageLength(73.0);

        Text value = new Text(SampleData.COMMENT_ROW);

        AverageMapper mapper = new AverageMapper();
        mapper.map(null, value, mockContext);

        verify(mockContext).write(expectedUserId, expectedAverage);
    }
}
