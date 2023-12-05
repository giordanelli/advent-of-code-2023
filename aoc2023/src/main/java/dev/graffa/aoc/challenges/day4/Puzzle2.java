package dev.graffa.aoc.challenges.day4;

import dev.graffa.aoc.challenges.Puzzle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Puzzle2 extends Puzzle {
  Map<Integer, Integer> cards = new HashMap<>();

  public Puzzle2(String fileInputPath) {
    super(fileInputPath);
  }


  public int score() {
    getWonCards();
    AtomicInteger atomicInteger = new AtomicInteger();
    return getInputContent().lines()
      .mapToInt(line -> availableCards(atomicInteger.getAndIncrement())).sum();
  }

  protected void getWonCards() {
    cards.clear();
    AtomicInteger atomicInteger = new AtomicInteger();
    getInputContent().lines()
      .forEach(line -> {
        int lineNumber = atomicInteger.getAndIncrement();
        int cardsAvailable = availableCards(lineNumber);
        int winningNumbersCount = winningNumberCount(line);
        for (int i = 0; i < winningNumbersCount; i++) {
          cards.put(lineNumber + i+1, cards.getOrDefault(lineNumber + i + 1, 1) + cardsAvailable);
        }
      });
  }

  protected int availableCards(int lineNumber) {
    return Optional.ofNullable(cards.get(lineNumber))
      .orElse(1);
  }

  protected int winningNumberCount(String line) {
    String[] strings = line.split(":")[1].trim().split("\\|");
    List<String> winningNumbers = Arrays.stream(strings[0].trim().split(" +"))
      .toList();
    Stream<String> myNumbers = Arrays.stream(strings[1].trim().split(" "));
    return (int) myNumbers.filter(winningNumbers::contains)
      .count();
  }

  @Override
  public void run() {
    System.out.println(score());
  }
}
