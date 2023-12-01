package dev.graffa.aoc.challenges.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle2Test {

  @Test
  public void testPuzzle(){
    Puzzle2 puzzle2 = new Puzzle2("day1/puzzle2.txt");
    assertEquals(281,puzzle2.calibration());
  }
}