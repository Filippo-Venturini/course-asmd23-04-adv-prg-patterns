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

The purpose of this part is to perform an equivalent parametrized test to the previous one but using `ScalaTest`.

For achieving this goal a *beforeAll()* function is created, in which a random list of pair `(Int, String)` is initialized, using `scala.util.Random`.
The number of tests to perform and the length of the strings generated can be set using the variables `numberOfTests` and `stringLength`.
The test has been rewritten using the *shouldBe* matchers of `ScalaTest`

### Conclusions

- `ScalaCheck` has some built-in generators that are really usefull and are not present in `ScalaTest`
- `ScalaCheck` also has all the necessary parameters that can be easily configured for realize custom tests. With `ScalaTest` insthead is necessary to create local variables for control for example the length of the generated values or the number of the executed tests.
- Both `ScalaCheck` and `ScalaTest` has a *forAll()* function that is really usefull for execute an assertion with multiple generated values
- With the consideration above, `ScalaCheck` is considered more handy and solid for setting up the configuration of the tests and generate the parameters.
