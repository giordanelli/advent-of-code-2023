package dev.graffa.aoc.challenges.day5;

import dev.graffa.aoc.challenges.Day;

import java.util.List;

public class Day5 extends Day {

  public Day5() {
    super(List.of(new Compass("day5.txt"),new CompassWithSeedsRange("day5.txt")));
  }

  public static void main(String[] args) {
    new Day5().run();
  }
}
