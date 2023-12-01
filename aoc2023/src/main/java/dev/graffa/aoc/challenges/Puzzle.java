package dev.graffa.aoc.challenges;

import dev.graffa.aoc.common.FileReader;

import java.io.IOException;

public abstract class Puzzle {
  protected String fileInputPath;

  public Puzzle(String fileInputPath) {
    this.fileInputPath = fileInputPath;
  }

  protected String getInputContent(){
    try {
      return FileReader.fileContent(fileInputPath);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public abstract void run();
}
