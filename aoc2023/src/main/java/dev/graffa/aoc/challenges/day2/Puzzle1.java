package dev.graffa.aoc.challenges.day2;

import dev.graffa.aoc.challenges.Puzzle;

public class Puzzle1 extends Puzzle {
  public Puzzle1(String fileInputPath) {
    super(fileInputPath);
  }

  @Override
  public void run() {
    int sum = parseInput();
    System.out.println(sum);
  }

  int parseInput() {
    int maxRed = 12, maxGreen = 13, maxBlue = 14;
    return getInputContent().lines()
      .map(this::parseLine)
      .filter(bag -> bag.green <= maxGreen && bag.red <= maxRed && bag.blue <= maxBlue)
      .mapToInt(value -> value.testNumber).sum();
  }

  private Bag parseLine(String line) {
    String[] strings = line.split(":");
    int testNumber = Integer.parseInt(strings[0].split(" ")[1]);
    Bag bag = new Bag(testNumber, 0, 0, 0);
    String game = strings[1].trim();
    String[] tests = game.split(";");
    for (String test : tests) {
      String[] cubes = test.split(",");
      for (String cube : cubes) {
        String[] info = cube.trim().split(" ");
        int cubesNumber = Integer.parseInt(info[0]);
        String cubeColor = info[1];
        switch (cubeColor) {
          case "red":
            bag.red = Math.max(bag.red, cubesNumber);
            break;
          case "green":
            bag.green = Math.max(bag.green, cubesNumber);
            break;
          case "blue":
            bag.blue = Math.max(bag.blue, cubesNumber);
            break;
          default:
            throw new IllegalArgumentException(String.format("Illegal color: %s", cubeColor));
        }

      }
    }
    return bag;
  }

  private static class Bag {
    protected int testNumber, red, green, blue;

    public Bag(int testNumber, int red, int green, int blue) {
      this.testNumber = testNumber;
      this.red = red;
      this.green = green;
      this.blue = blue;
    }
  }
}
