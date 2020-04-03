#Java-Validator

A small Java library to facilitate declarative validation of Java object state.


#### Example Usage

```java
// Build a SynchronousValidator with a String subject and a String context.
SynchronousValidator<String, String> validator = ValidatorBuilder.<String, String>builder()
  .add(requireNotNull("subject.cannot.be.null"))
  .add(Constraints.violation("subject.cannot.be.blank").forConstraint((subject, context) -> !subject.isBlank()))
  .buildSynchronous();

Violations result = validator.validate("non null subject");

for (Violation violation : result) {
  System.out.println(violation);
}
```

[Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0)