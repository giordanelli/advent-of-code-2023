package dev.graffa.aoc.challenges.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle1Test {

  @Test
  public void testPuzzle(){
    Puzzle1 puzzle1 = new Puzzle1("day1/puzzle1.txt");
    assertEquals(142,puzzle1.calibration());
  }
}