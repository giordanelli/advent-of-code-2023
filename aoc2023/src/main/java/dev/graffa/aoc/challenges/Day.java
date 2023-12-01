package dev.graffa.aoc.challenges;

import java.util.List;

public abstract class Day {
  List<Puzzle> puzzles;

  public Day(List<Puzzle> puzzles) {
    this.puzzles = puzzles;
  }

  public final void run() {
    puzzles.forEach(Puzzle::run);
  }
}
