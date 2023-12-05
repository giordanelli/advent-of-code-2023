package dev.graffa.aoc.challenges.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Puzzle2Test {

  @Test
  public void testPuzzle() {
    Puzzle2 puzzle2 = new Puzzle2("day4/day4.txt");
    assertEquals(30, puzzle2.score());

  }
}