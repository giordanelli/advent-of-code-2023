package dev.graffa.aoc.challenges.day1;

import dev.graffa.aoc.challenges.Puzzle;

public class Puzzle1 extends Puzzle {

  public Puzzle1(String fileInputPath) {
    super(fileInputPath);
  }

  @Override
  public void run() {
    System.out.println(calibration());
  }

  public int calibration(){
    return getInputContent().lines()
      .mapToInt(this::digits).sum();
  }

  protected int digits(String line) {
    char[] charArray = line.toCharArray();
    int firstDigit = 0, lastDigit = 0;
    for (char c : charArray) {
      if (Character.isDigit(c)) {
        if (firstDigit ==0) {
          firstDigit = Character.getNumericValue(c);
        }
        lastDigit= Character.getNumericValue(c);
      }
    }
    return firstDigit*10+lastDigit;
  }
}
