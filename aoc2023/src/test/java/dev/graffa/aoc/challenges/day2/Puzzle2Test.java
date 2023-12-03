package dev.graffa.aoc.challenges.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle2Test {

  @Test
  public void testPuzzle(){
    Puzzle2 puzzle2 = new Puzzle2("day2/puzzle.txt");
    assertEquals(2286,puzzle2.parseInput());
  }
}