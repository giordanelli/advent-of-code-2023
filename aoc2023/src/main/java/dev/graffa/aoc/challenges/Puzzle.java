package dev.graffa.aoc.challenges;

import dev.graffa.aoc.common.FileReader;

import java.io.IOException;

public abstract class Puzzle {
  protected String fileInputPath;

  public Puzzle(String fileInputPath) {
    this.fileInputPath = fileInputPath;
  }

  public final void readInputAndRun() {
    String fileContent;
    try {
      fileContent = FileReader.fileContent(fileInputPath);
    } catch (IOException e) {
      throw new RuntimeException("Unable to read input file", e);
    }
    run(fileContent);
  }

  public abstract void run(String input);
}
