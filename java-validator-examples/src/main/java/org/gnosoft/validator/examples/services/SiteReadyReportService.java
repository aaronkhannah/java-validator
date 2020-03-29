package org.gnosoft.validator.examples.services;

import java.util.Collection;
import java.util.Map;

import static java.lang.String.join;
import static java.lang.System.out;

public class SiteReadyReportService {
  public void generateReport(Map<Long, Collection<String>> productValidationResults) {
    productValidationResults.forEach((key, value) -> {
      if (value.isEmpty()) {
        out.println(join(" ", key.toString(), "is VALID."));
      } else {
        out.println(join(" ", key.toString(), "is INVALID."));
        value.forEach(violation -> out.println(join("", "\t", violation)));
      }
    });
  }
}
