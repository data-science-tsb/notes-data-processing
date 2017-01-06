package com.hadooppractice.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.hadooppractice.utils.XMLParser;

public class XMLParserTest {

	@Test
    void myFirstTest() {
        String sampleXml = "  <row Id=\"1\" TagName=\".net\" Count=\"244006\" ExcerptPostId=\"3624959\" WikiPostId=\"3607476\" />";
        Map<String, String> parsed = XMLParser.toMap(sampleXml);
        
        assertEquals("1", parsed.get("Id"));
    }
}
