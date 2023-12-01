package dev.graffa.aoc.challenges.day1;

import java.util.Map;

public class Puzzle2 extends Puzzle1 {

  public Puzzle2(String fileInputPath) {
    super(fileInputPath);
  }

  @Override
  protected int digits(String line) {
    char[] charArray = line.toCharArray();
    int firstDigit = 0, lastDigit = 0;
    for (int i = 0; i < charArray.length; i++) {
      int isdigitStringAt = isdigitStringAt(line, i);

      if (isdigitStringAt > 0) {
        if (firstDigit == 0) {
          firstDigit = isdigitStringAt;
        }
        lastDigit = isdigitStringAt;
      }
    }
    return firstDigit * 10 + lastDigit;
  }

  private int isdigitStringAt(String input, int position) {
    char charAt = input.charAt(position);
    if (Character.isDigit(charAt)) {
      return Character.getNumericValue(charAt);
    }

    Map<String, Integer> numbers = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7,
      "eight", 8, "nine", 9);
    for (String number : numbers.keySet()) {
      int numberLength = number.length();
      if (position > input.length() - numberLength) {
        continue;
      }
      String substring = input.substring(position, position + numberLength);
      if (number.equals(substring)) {
        return numbers.get(substring);
      }
    }
    return -1;
  }
}
