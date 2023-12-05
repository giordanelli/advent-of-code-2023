package dev.graffa.aoc.challenges.day4;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Puzzle1Test {

  @Test
  public void testPuzzle(){
    Puzzle1 puzzle1 = new Puzzle1("day4/day4.txt");
    assertEquals(13,puzzle1.score());
  }
}