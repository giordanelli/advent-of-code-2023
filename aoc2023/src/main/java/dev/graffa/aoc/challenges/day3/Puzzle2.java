package dev.graffa.aoc.challenges.day3;

import dev.graffa.aoc.challenges.Puzzle;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Puzzle2 extends Puzzle {
  protected static final char SEPARATOR = '.';
  protected List<String> input;

  public Puzzle2(String fileInputPath) {
    super(fileInputPath);
    input = getInputContent().lines()
      .toList();
  }

  @Override
  public void run() {
    System.out.println(getRatio());
  }

  int getRatio() {
    List<NextInt> nextIntList = engineParts().toList();
    return gears().mapToInt(gear -> gear.getRatio(nextIntList)).sum();
  }

  Stream<Gear> gears() {
    AtomicInteger counter = new AtomicInteger();
    return input.stream()
      .flatMap(line -> {
        int lineNumber = counter.getAndIncrement();
        return GearIterator.getNextGears(line, lineNumber)
          .stream();
      });
  }

  Stream<NextInt> engineParts() {
    AtomicInteger counter = new AtomicInteger();
    return input.stream()
      .flatMap(line -> {
        int lineNumber = counter.getAndIncrement();
        return NextIntIterator.getNextInts(line, lineNumber)
          .stream();
      })
      .filter(nextInt -> hasAdjacentSymbols(nextInt.lineNumber, nextInt.start, nextInt.end));
  }

  protected boolean hasAdjacentSymbols(int line, int start, int end) {
    boolean hasSymbolsOnTop = false, hasSymbolsOnTheSide = false, hasSymbolsOnBottom = false;
    String thisLine = input.get(line);
    if (start > 0) {
      hasSymbolsOnTheSide |= isSymbol(thisLine.charAt(start - 1));
    }
    if (end < thisLine.length() - 1) {
      hasSymbolsOnTheSide |= isSymbol(thisLine.charAt(end + 1));
    }
    if (line > 0) {
      String previousLine = input.get(line - 1);
      int previousLineStart = Math.max(0, start - 1);
      int previousLineEnd = Math.min(previousLine.length() - 1, end + 1);
      for (int i = previousLineStart; i <= previousLineEnd; i++) {
        hasSymbolsOnTop |= isSymbol(previousLine.charAt(i));
      }
    }
    if (line < input.size() - 1) {
      String nextLine = input.get(line + 1);
      int nextLineStart = Math.max(0, start - 1);
      int nextLineEnd = Math.min(nextLine.length() - 1, end + 1);
      for (int i = nextLineStart; i <= nextLineEnd; i++) {
        hasSymbolsOnBottom |= isSymbol(nextLine.charAt(i));
      }
    }
    return hasSymbolsOnTop || hasSymbolsOnBottom || hasSymbolsOnTheSide;
  }

  protected boolean isSymbol(char c) {
    return !Character.isDigit(c) && c != SEPARATOR;
  }

  protected static class GearIterator {
    static List<Gear> getNextGears(String line, int lineNumber) {
      List<Gear> gears = new java.util.ArrayList<>(List.of());
      Pattern integerPattern = Pattern.compile("\\*");
      Matcher matcher = integerPattern.matcher(line);
      while (matcher.find()) {
        int start = matcher.start();
        gears.add(new Gear(lineNumber, start));
      }
      return gears;
    }

  }

  protected static class NextIntIterator {
    static List<NextInt> getNextInts(String line, int lineNumber) {
      List<NextInt> integers = new java.util.ArrayList<>(List.of());
      Pattern integerPattern = Pattern.compile("\\d+");
      Matcher matcher = integerPattern.matcher(line);
      while (matcher.find()) {
        int value = Integer.parseInt(matcher.group());
        int start = matcher.start();
        int end = matcher.end();
        integers.add(new NextInt(start, end - 1, value, lineNumber));
      }
      return integers;
    }
  }

  protected static class Gear {
    protected int lineNumber, position;

    public Gear(int lineNumber, int position) {
      this.lineNumber = lineNumber;
      this.position = position;
    }

    public int getRatio(List<NextInt> nextInts) {
      int adjacentNumbers = 0, ratio = 1;
      for (NextInt nextInt : nextInts) {
        if (isAdjacent(nextInt)) {
          ratio *= nextInt.value;
          adjacentNumbers++;
        }
      }
      if (adjacentNumbers == 2) {
        return ratio;
      }
      return 0;
    }

    public boolean isAdjacent(NextInt nextInt) {
      if (nextInt.lineNumber == lineNumber) {
        return nextInt.start == position + 1 || nextInt.end == position - 1;
      }
      if (nextInt.lineNumber == lineNumber + 1 || nextInt.lineNumber == lineNumber - 1) {
        return position >= nextInt.start - 1 && position <= nextInt.end + 1;
      }
      return false;
    }
  }

  protected static class NextInt {
    protected int start, end, value, lineNumber;

    public NextInt(int start, int end, int value, int lineNumber) {
      this.start = start;
      this.end = end;
      this.value = value;
      this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
      return "NextInt{" + "start=" + start + ", end=" + end + ", value=" + value + ", lineNumber=" + lineNumber + '}';
    }
  }
}
