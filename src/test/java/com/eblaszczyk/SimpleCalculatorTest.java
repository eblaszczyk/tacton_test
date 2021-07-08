package com.eblaszczyk;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleCalculatorTest {

  private SimpleCalculator calculator = new SimpleCalculator();

  @Test
  public void shouldReturnNumberWhenNoOperation() {
    assertThat(calculator.calculate("-1")).isEqualTo(-1);
    assertThat(calculator.calculate("1")).isEqualTo(1);
    assertThat(calculator.calculate("-123")).isEqualTo(-123);
  }

  @Test
  public void shouldCalculateExpresion() {
    assertThat(calculator.calculate("-123 + 123 * 1 * 3")).isEqualTo(246);
    assertThat(calculator.calculate("-123 + 123 / 1 + 2 * 3")).isEqualTo(6);
    assertThat(calculator.calculate("-123 + 123 / 1 + 2 * -3")).isEqualTo(-6);
  }

  @Test
  public void shouldThrowExceptionDueInvalidInput() {
    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
        calculator.calculate("invalid input"));

    assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
        calculator.calculate("-5 * 6 a 4"));

  }

  @Test
  public void shouldTrimAndNoThrowException() {
    Assertions.assertThatNoException().isThrownBy(() ->
        calculator.calculate(" -123 + 123 / 1 + 2 * -3 "));
  }


}