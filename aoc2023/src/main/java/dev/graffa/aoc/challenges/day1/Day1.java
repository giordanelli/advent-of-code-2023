package dev.graffa.aoc.challenges.day1;

import dev.graffa.aoc.challenges.Day;

import java.util.List;

public class Day1 extends Day {
  public Day1() {
    super(List.of(new Puzzle1("day1.txt"), new Puzzle2("day1.txt")));
  }

  public static void main(String[] args) {
    new Day1().run();
  }
}
