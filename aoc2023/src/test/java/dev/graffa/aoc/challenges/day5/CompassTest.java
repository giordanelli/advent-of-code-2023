package dev.graffa.aoc.challenges.day5;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompassTest {

  @Test
  void minimumLocation() {
    Compass compass = new Compass("day5/day5.txt");
    assertEquals(new BigInteger("35"),compass.minimumLocation());
  }
}