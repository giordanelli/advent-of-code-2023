package dev.graffa.aoc.challenges.day2;

import dev.graffa.aoc.challenges.Puzzle;

public class Puzzle2 extends Puzzle {
  public Puzzle2(String fileInputPath) {
    super(fileInputPath);
  }

  @Override
  public void run() {
    int sum = parseInput();
    System.out.println(sum);
  }

  int parseInput() {
    return getInputContent().lines()
      .map(this::parseLine)
      .mapToInt(Bag::power).sum();
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

    public int power() {
      return red * green * blue;
    }
  }
}
