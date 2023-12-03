package dev.graffa.aoc.challenges.day2;

import dev.graffa.aoc.challenges.Day;

import java.util.List;

public class Day2 extends Day {
  public Day2() {
    super(List.of(new Puzzle1("day2.txt"),new Puzzle2("day2.txt")));
  }

  public static void main(String[] args) {
    new Day2().run();
  }
}
