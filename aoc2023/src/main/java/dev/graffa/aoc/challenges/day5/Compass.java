package dev.graffa.aoc.challenges.day5;

import dev.graffa.aoc.challenges.Puzzle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.FERTILIZER_TO_WATER;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.HUMIDITY_TO_LOCATION;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.LIGHT_TO_TEMPERATURE;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.SEED_TO_SOIL;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.SOIL_TO_FERTILIZER;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.TEMPERATURE_TO_HUMIDITY;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.WATER_TO_LIGHT;

public class Compass extends Puzzle {
  protected List<BigInteger> seeds = new ArrayList<>();
  protected Map<IndicationType, List<Indication>> indicationsMap = new HashMap<>();

  public Compass(String fileInputPath) {
    super(fileInputPath);
  }

  public void setIndication(String input) {
    AtomicReference<IndicationType> currentType = new AtomicReference<>();
    input.lines()
      .forEach(line -> {
        if (line.equals("\n") || line.isBlank() || line.isEmpty()) {
          return;
        }
        if (line.startsWith("seeds")) {
          seeds.addAll(Arrays.stream(line.split(":")[1].trim().split(" "))
            .map(BigInteger::new)
            .toList());
        } else if (line.startsWith("seed-to-soil")) {
          currentType.set(SEED_TO_SOIL);
        } else if (line.startsWith("soil-to-fertilizer")) {
          currentType.set(SOIL_TO_FERTILIZER);
        } else if (line.startsWith("fertilizer-to-water")) {
          currentType.set(FERTILIZER_TO_WATER);
        } else if (line.startsWith("water-to-light")) {
          currentType.set(WATER_TO_LIGHT);
        } else if (line.startsWith("light-to-temperature")) {
          currentType.set(LIGHT_TO_TEMPERATURE);
        } else if (line.startsWith("temperature-to-humidity")) {
          currentType.set(TEMPERATURE_TO_HUMIDITY);
        } else if (line.startsWith("humidity-to-location")) {
          currentType.set(HUMIDITY_TO_LOCATION);
        } else {
          Indication indication = Indication.fromString(line);
          if (!indicationsMap.containsKey(currentType.get())) {
            indicationsMap.put(currentType.get(), new ArrayList<>());
          }
          List<Indication> indications = indicationsMap.get(currentType.get());
          indications.add(indication);
        }
      });
  }

  public BigInteger minimumLocation() {
    setIndication(getInputContent());
    return seeds.stream()
      .map(this::getDestination)
      .min(BigInteger::compareTo)
      .orElse(BigInteger.ZERO);
  }

  protected BigInteger getDestination(BigInteger seed) {
    BigInteger soil = getLocation(seed, SEED_TO_SOIL);
    BigInteger fertilizer = getLocation(soil, SOIL_TO_FERTILIZER);
    BigInteger water = getLocation(fertilizer, FERTILIZER_TO_WATER);
    BigInteger light = getLocation(water, WATER_TO_LIGHT);
    BigInteger temperature = getLocation(light, LIGHT_TO_TEMPERATURE);
    BigInteger humidity = getLocation(temperature, TEMPERATURE_TO_HUMIDITY);
    return getLocation(humidity, HUMIDITY_TO_LOCATION);
  }

  protected BigInteger getLocation(BigInteger source, IndicationType indicationType) {
    List<Indication> indications = indicationsMap.get(indicationType);
    for (Indication indication : indications) {
      if (indication.containsCoordinates(source)) {
        return indication.to(source);
      }
    }
    return source;
  }

  protected enum IndicationType {
    SEED_TO_SOIL,
    SOIL_TO_FERTILIZER,
    FERTILIZER_TO_WATER,
    WATER_TO_LIGHT,
    LIGHT_TO_TEMPERATURE,
    TEMPERATURE_TO_HUMIDITY,
    HUMIDITY_TO_LOCATION
  }

  protected static class Indication {
    protected BigInteger sourceRangeStart, destinationRangeStart, rangeLength;

    protected Indication(BigInteger sourceRangeStart, BigInteger destinationRangeStart, BigInteger rangeLength) {
      this.sourceRangeStart = sourceRangeStart;
      this.destinationRangeStart = destinationRangeStart;
      this.rangeLength = rangeLength;
    }

    public static Indication fromString(String line) {
      String[] elements = line.trim().split(" ");
      BigInteger destinationRangeStart = new BigInteger(elements[0]);
      BigInteger sourceRangeStart = new BigInteger((elements[1]));
      BigInteger rangeLength = new BigInteger((elements[2]));
      return new Indication(sourceRangeStart, destinationRangeStart, rangeLength);
    }

    public boolean containsCoordinates(BigInteger from) {
      return from.compareTo(sourceRangeStart) >= 0 && from.compareTo(sourceRangeStart.add(rangeLength)) <= 0;
    }

    public BigInteger to(BigInteger from) {
      if (containsCoordinates(from)) {
        return destinationRangeStart.add(from)
          .subtract(sourceRangeStart);
      }
      throw new IllegalArgumentException("outside of range " + from);
    }
  }

  @Override
  public void run() {
    System.out.println(minimumLocation());
  }
}
