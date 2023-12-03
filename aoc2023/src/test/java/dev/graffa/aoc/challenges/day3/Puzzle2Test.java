package dev.graffa.aoc.challenges.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle2Test {

  @Test
  public void testPuzzle() {
    Puzzle2 puzzle2 = new Puzzle2("day3/day3.txt");
    assertEquals(467835, puzzle2.getRatio());
  }
}