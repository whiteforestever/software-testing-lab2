package com.demo.function;

import java.math.BigDecimal;

public interface ExpandableFunction {

  BigDecimal calculate(final BigDecimal x, final BigDecimal precision);

}
