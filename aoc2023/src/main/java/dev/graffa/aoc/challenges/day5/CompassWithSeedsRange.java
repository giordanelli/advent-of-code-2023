package dev.graffa.aoc.challenges.day5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.FERTILIZER_TO_WATER;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.HUMIDITY_TO_LOCATION;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.LIGHT_TO_TEMPERATURE;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.SEED_TO_SOIL;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.SOIL_TO_FERTILIZER;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.TEMPERATURE_TO_HUMIDITY;
import static dev.graffa.aoc.challenges.day5.Compass.IndicationType.WATER_TO_LIGHT;

public class CompassWithSeedsRange extends Compass {
  public CompassWithSeedsRange(String fileInputPath) {
    super(fileInputPath);
  }

  @Override
  public void setIndication(String input) {
    AtomicReference<IndicationType> currentType = new AtomicReference<>();
    input.lines()
      .forEach(line -> {
        if (line.equals("\n") || line.isBlank() || line.isEmpty()) {
          return;
        }
        if (line.startsWith("seeds")) {
          String[] strings = line.split(":")[1].trim().split(" ");
          BigInteger start = BigInteger.valueOf(0);
          for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
              start = new BigInteger(strings[i]);
            } else {
              BigInteger val = new BigInteger(strings[1]);
              for (BigInteger j = BigInteger.valueOf(0); j.compareTo(val) < 0; j = j.add(new BigInteger("1"))) {
                seeds.add(start.add(j));
              }
            }
          }
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

}
