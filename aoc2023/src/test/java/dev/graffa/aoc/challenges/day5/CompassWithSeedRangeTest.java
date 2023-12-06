package dev.graffa.aoc.challenges.day5;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompassWithSeedRangeTest {

  @Test
  void minimumLocation() {
    Compass compass = new CompassWithSeedsRange("day5/day5.txt");
    assertEquals(new BigInteger("46"),compass.minimumLocation());
  }
}