package dev.graffa.aoc.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class FileReader {
  public static String fileContent(String filePath) throws IOException {
    return IOUtils.resourceToString(filePath, StandardCharsets.UTF_8, FileReader.class.getClassLoader());
  }

}