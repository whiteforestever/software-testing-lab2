package com.demo.trigonometric;

import ch.obermuhlner.math.big.BigDecimalMath;
import com.demo.function.LimitedIterations;

import static java.lang.String.format;
import static java.math.BigDecimal.ZERO;
import static java.math.MathContext.DECIMAL128;
import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.math.MathContext;

public class  Cot extends LimitedIterations {

  private final Sin sin;
  private final Cos cos;

  public Cot(final Sin sin, final Cos cos) {
    super();
    this.sin = sin;
    this.cos = cos;
  }

  public Cot() {
    super();
    this.sin = new Sin();
    this.cos = new Cos();
  }

  @Override
  public BigDecimal calculate(final BigDecimal x, final BigDecimal precision) {
    checkValidity(x, precision);
    final BigDecimal newX = getValueInFirstPeriod(x);

    final BigDecimal sinValue = sin.calculate(newX, precision);
    final BigDecimal cosValue = cos.calculate(newX, precision);

    if (sinValue.compareTo(ZERO) == 0) {
      return null;
    }

    final BigDecimal result = cosValue.divide(sinValue, DECIMAL128.getPrecision(), HALF_EVEN);
    return result.setScale(precision.scale(), HALF_EVEN);
  }

  private BigDecimal getValueInFirstPeriod(BigDecimal x){
      final MathContext mc = new MathContext(DECIMAL128.getPrecision(), HALF_EVEN);
      final BigDecimal PI2 = BigDecimalMath.pi(mc).multiply(new BigDecimal(2));

      while (x.compareTo(ZERO) >= 0) {
        x = x.subtract(PI2);
      }

      while (x.compareTo(ZERO) < 0) {
          x = x.add(PI2);
      }
      return x;
    }
}
