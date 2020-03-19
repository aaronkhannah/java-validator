package org.gnosoft.validator;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ViolationsTest {
  @Test
  public void buildAndIterate() {
    Collection<Violation> collection = Arrays.asList(
      Violation.of("a.violation.message"),
      Violation.of("another.violation.message")
    );

    Violations violations = Violations.of(collection.stream()
      .map(Violation::toString)
      .toArray(String[]::new));

    assertThat(violations)
      .isNotNull()
      .isNotEmpty();

    for (Violation violation : violations) {
      assertThat(collection).contains(violation);
    }
  }

  @Test
  public void buildAndStream() {
    Collection<Violation> collection = Arrays.asList(
      Violation.of("a.violation.message"),
      Violation.of("another.violation.message")
    );

    Violations violations = Violations.of(collection.stream()
      .map(Violation::toString)
      .toArray(String[]::new));

    assertThat(violations)
      .isNotNull()
      .isNotEmpty();

    assertThat(violations.stream().map(Violation::toString).collect(Collectors.toList()))
      .containsAll(collection.stream().map(Violation::toString).collect(Collectors.toList()));
  }
}
