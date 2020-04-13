# Java-Validator

A small Java library to facilitate declarative validation of Java object state.


#### Example Usage

```java
// Build a SynchronousValidator with a String subject and a String context.
SynchronousValidator<String, String> validator = ValidatorBuilder.<String, String>builder()
  .add(Constraints.requireNotNull("subject.cannot.be.null"))
  .add(Constraints.violation("subject.cannot.be.blank").forConstraint((subject, context) -> !subject.isBlank()))
  .buildSynchronous();

Violations result = validator.validate("non null subject");

// Should produce nothing.
for (Violation violation : result) {
  System.out.println(violation);
}

result = validator.validate("");

// Should produce "subject.cannot.be.blank".
for (Violation violation : result) {
  System.out.println(violation);
}
```

This software provided under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0).