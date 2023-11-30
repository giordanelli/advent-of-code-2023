package dev.graffa.aoc.common;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

  @Test
  public void testReadLines() throws IOException {
    String expected = "Line 1\nLine 2";
    assertEquals(expected, FileReader.fileContent("fileReaderTest.txt"));
  }
}