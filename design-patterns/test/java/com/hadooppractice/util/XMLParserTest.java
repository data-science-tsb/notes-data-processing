package com.hadooppractice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import com.hadooppractice.testutils.SampleData;
import org.junit.jupiter.api.Test;

import com.hadooppractice.utils.XMLParser;

public class XMLParserTest {

	@Test
    void myFirstTest() {
        Map<String, String> parsed = XMLParser.toMap(SampleData.COMMENT_ROW);
        
        assertEquals("2579740", parsed.get("Id"));
        assertEquals("2573882", parsed.get("PostId"));
    }
}
