# Lab04 - Advanced Programming

## Task 1 - Test-Operate

In the first phase, a Scala object is created at the following path: *test.scala.u04.datastructures.Task1SequenceCheck.scala*. Here, there is a property for testing with `ScalaCheck` to verify that the "of" operator is a correct factory.

Before the property, the following parameters of `ScalaCheck` are tested:

- `withMinSuccessfulTests(int)`: Sets the minimum number of successful tests before a property is considered passed.
- `withMaxDiscardRatio(float)`: Sets the maximum ratio between discarded and succeeded tests before ScalaCheck gives up and discards the property.
- `withMinSize(int)`: Sets the minimum size to use for generated values.
- `withMaxSize(int)`: Sets the maximum size to use for generated values.
- `withWorkers(int)`: Sets the number of worker threads to use during testing.

In the second phase, a Scala object is created at the following path: *"test.scala.u04.datastructures.Task1SequenceTest.scala"*.

The purpose of this part is to perform an equivalent parameterized test to the previous one but using `ScalaTest`.

To achieve this goal, a *beforeAll()* function is created, in which a random list of pairs `(Int, String)` is initialized, using `scala.util.Random`. The number of tests to perform and the length of the strings generated can be set using the variables `numberOfTests` and `stringLength`. The test has been rewritten using the *shouldBe* matchers of `ScalaTest`.

### Conclusions

- `ScalaCheck` has some built-in generators that are really useful and are not present in `ScalaTest`.
- `ScalaCheck` also has all the necessary parameters that can be easily configured to realize custom tests. With `ScalaTest`, instead, it is necessary to create local variables for controlling, for example, the length of the generated values or the number of executed tests.
- Both `ScalaCheck` and `ScalaTest` have a *forAll()* function that is really useful for executing an assertion with multiple generated values.
- With the considerations above, `ScalaCheck` is considered more handy and solid for setting up the configuration of the tests and generating the parameters.

## Task 2 - ADT Verifier

With this task, an Abstract Data Type for representing a Sequence is created with two different implementations and then tested using `ScalaCheck`.

The implementation of the ADT corresponding to the Sequence can be found at the following path: *package scala.u04.task2.Sequences.scala*.

First of all, using a DSL, the specification of the Sequence type is described, in particular: type, constructors, operations, and axioms.

The following operations are defined: concat, filter, map, flatmap, fold and reduce.

Then the specification is turned into a Scala trait, defining all the functionalities that a Sequence offers. Afterwards, two implementations are realized, one using the `Cons/Nil` approach, the other using `Scala List`.

### Cons/Nil implementation

In this first implementation, a private `enum` defines a Sequence using `Cons/Nil`, and then, using an `opaque` alias, the type using this implementation is defined, making it invisible from outside.

Then, all the methods are implemented, following the axioms definition of the ADT and using the `Cons/Nil` private implementation.

### Scala List implementation

This implementation is realized by simply defining Scala.List as the opaque type of Sequence, and then implementing all the methods by calling the corresponding ones already provided by List.

With this approach, in both implementations, two constructors are available for building a Sequence, but this makes this Data Type not usable, for example in a `match` because it lacks an `unapply`. So one possible solution for this problem could be to add a getter `getCons` that works as unapply and provides an `Option[(A, Sequence[A])]` that can be used as an **extractor**.

### Tests

The aim of this part is to verify that both implementations work by writing one test for each axiom defined in the ADT specifications, using `ScalaCheck`.

The code can be found at the path: *package scala.u04.task2.SequenceCheck.scala*.

To demonstrate that both implementations satisfy all the tests, a function that tests a SequenceADT is created and then invoked two times with the two different implementations.

Inside the function, following the axioms defined in the ADT specifications, all the tests make assertions and check that every operation of the Sequence works as expected.

Built-in generators are used for the parameters of the tests, particularly for generating a random Sequence (monadically), mappers, and filters.

