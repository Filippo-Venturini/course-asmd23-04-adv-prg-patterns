# Lab04 - Advanced Programming

## Task 1 - Test-Operate

In the first phase a Scala object is created at the following path: *test.scala.u04.datastructures.Task1SequenceCheck.scala*.
Here there is a proprety for test with `ScalaCheck` that the "of" operator is a correct factory. 

Before the proprety the following parameters of `ScalaCheck` are tested:

- `withMinSuccessfulTests(int)`: Sets the minimum number of successful tests before a property is considered passed.
- `withMaxDiscardRatio(float)`: Sets the maximum ratio between discarded and succeeded tests before ScalaCheck gives up and discards the property.
- `withMinSize(int)`: Sets the minimum size to use for generated values.
- `withMaxSize(int)`: Sets the maximum size to use for generated values.
- `withWorkers(int)`: Sets the number of worker threads to use during testing.

In the second phase a Scala object is created at the following path: *"test.scala.u04.datastructures.Task1SequenceTest.scala"*.
