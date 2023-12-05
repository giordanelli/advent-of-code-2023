package dev.graffa.aoc.challenges.day4;

import dev.graffa.aoc.challenges.Puzzle;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Puzzle1 extends Puzzle {
  public Puzzle1(String fileInputPath) {
    super(fileInputPath);
  }


  public int score() {
    return getInputContent().lines()
      .mapToInt(this::winningNumbersScore).sum();
  }

  protected int winningNumbersScore(String line) {
    String[] strings = line.split(":")[1].trim().split("\\|");
    List<String> winningNumbers = Arrays.stream(strings[0].trim().split(" +"))
      .toList();
    Stream<String> myNumbers = Arrays.stream(strings[1].trim().split(" "));
    int winningNumbersCount = (int) myNumbers.filter(winningNumbers::contains)
      .count();
    if (winningNumbersCount == 0) {
      return 0;
    }
    if (winningNumbersCount == 1) {
      return 1;
    }
    return (int) Math.pow (2,winningNumbersCount - 1);
  }

  @Override
  public void run() {
    System.out.println(score());
  }
}
