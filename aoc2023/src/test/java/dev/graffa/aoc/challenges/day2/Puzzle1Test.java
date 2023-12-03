package dev.graffa.aoc.challenges.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle1Test {

  @Test
  public void testPuzzle(){
    Puzzle1 puzzle1 = new Puzzle1("day2/puzzle.txt");
    assertEquals(8,puzzle1.parseInput());
  }
}