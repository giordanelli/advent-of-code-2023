package dev.graffa.aoc.challenges.day3;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle1Test {

  @Test
  public void testPuzzle(){
    Puzzle1 puzzle1 = new Puzzle1("day3/day3.txt");
    assertEquals(4361,puzzle1.engineParts());
  }
}