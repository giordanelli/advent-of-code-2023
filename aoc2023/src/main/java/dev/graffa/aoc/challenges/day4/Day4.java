package dev.graffa.aoc.challenges.day4;

import dev.graffa.aoc.challenges.Day;

import java.util.List;

public class Day4 extends Day {
  public Day4() {
    super(List.of(new Puzzle1("day4.txt"), new Puzzle2("day4.txt")));
  }

  public static void main(String[] args) {
    new Day4().run();
  }
}
