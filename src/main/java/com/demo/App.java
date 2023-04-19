package com.demo;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.demo.function.FunctionsSystem;
import com.demo.logariphmic.Ln;
import com.demo.logariphmic.Log;
import com.demo.trigonometric.Cos;
import com.demo.trigonometric.Cot;
import com.demo.trigonometric.Sin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

public class App {

    public static void main(String[] args) throws IOException {
        final Sin sin = new Sin();
        final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
        final BigDecimal startValue = BigDecimal.ZERO;
        final BigDecimal endValue = startValue.add(
                BigDecimalMath.pi(mc)
                .multiply(new BigDecimal(2)));
        final BigDecimal stepValue = BigDecimalMath.pi(mc)
                .divide(new BigDecimal(24), DECIMAL128.getPrecision(), HALF_EVEN);
        CsvWriter.write(
                "docs/csv/sin.csv",
                sin,
                startValue,
                endValue,
                stepValue,
                new BigDecimal("0.000001"));

        final Cos cos = new Cos();
        CsvWriter.write(
                "docs/csv/cos.csv",
                cos,
                startValue,
                endValue,
                stepValue,
                new BigDecimal("0.000001"));

        final Cot cot = new Cot();
        CsvWriter.write(
                "docs/csv/cot.csv",
                cot,
                startValue,
                endValue,
                stepValue,
                new BigDecimal("0.000001"));

        final Ln ln = new Ln();
        CsvWriter.write(
                "docs/csv/ln.csv",
                ln,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.000001"));

        final Log log3 = new Log(3);
        CsvWriter.write(
                "docs/csv/log3.csv",
                log3,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.000001"));

        final Log log5 = new Log(5);
        CsvWriter.write(
                "docs/csv/log5.csv",
                log5,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.000001"));

        final Log log10 = new Log(10);
        CsvWriter.write(
                "docs/csv/log10.csv",
                log10,
                new BigDecimal(1),
                new BigDecimal(20),
                new BigDecimal("0.1"),
                new BigDecimal("0.000001"));

        final FunctionsSystem func = new FunctionsSystem();
        CsvWriter.write(
                "docs/csv/func.csv",
                func,
                startValue.subtract(endValue),
                endValue,
                stepValue.multiply(new BigDecimal("2")),
                new BigDecimal("0.000001"));
    }
}
