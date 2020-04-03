/*
 * Copyright (c) 2020. Aaron Hannah
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
