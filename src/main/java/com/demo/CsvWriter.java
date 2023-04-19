package com.demo;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.demo.function.Function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;


public class CsvWriter {

  public static void write(
          final String filename,
          final Function function,
          final BigDecimal from,
          final BigDecimal to,
          final BigDecimal step,
          final BigDecimal precision)
          throws IOException {
    final Path path = Paths.get(filename);
    Files.deleteIfExists(path);
    Files.createFile(path);
    try (PrintWriter printWriter = new PrintWriter(new FileWriter(filename))) {
      for (BigDecimal current = from; current.compareTo(to) <= 0; current = current.add(step)) {
        BigDecimal functionValue = function.calculate(current.setScale(12, RoundingMode.HALF_EVEN).stripTrailingZeros(), precision);
        if (functionValue == null) {
          printWriter.println(current.setScale(9, RoundingMode.HALF_EVEN).stripTrailingZeros() + "," + functionValue);
        } else {
          functionValue = functionValue.setScale(9, RoundingMode.HALF_EVEN);
          printWriter.println(current.setScale(9, RoundingMode.HALF_EVEN).stripTrailingZeros() + "," + functionValue.stripTrailingZeros());
        }
      }
    }
  }

}
