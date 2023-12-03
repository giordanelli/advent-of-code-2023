package dev.graffa.aoc.challenges.day3;

import dev.graffa.aoc.challenges.Day;

import java.util.List;

public class Day3 extends Day {
  public Day3() {
    super(List.of(new Puzzle1("day3.txt"), new Puzzle2("day3.txt")));
  }

  public static void main(String[] args) {
    new Day3().run();
  }
}
