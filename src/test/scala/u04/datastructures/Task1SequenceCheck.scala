package scala.u04.datastructures

import org.scalacheck.{Gen, Properties}
import org.scalacheck.Prop.forAll
import org.scalacheck.Arbitrary.arbitrary
import u04.datastructures.Sequences.*
import Sequence.*
import org.scalacheck.Test.Parameters

object Task1SequenceCheck extends Properties("SequenceTest"):

  def smallInt(): Gen[Int] = Gen.choose(0, 100)

  def sequenceGen(): Gen[Sequence[Int]] = for
    size <- Gen.choose(0, 100) // Use the Gen.choose method to determine the size of the sequence
    elements <- Gen.listOfN(size, Gen.choose(0, 100)) // Generate a list of random integers
  yield elements.foldRight[Sequence[Int]](Nil())((e, acc) => Cons(e, acc)) // Convert the list to a sequence
  
  override def overrideParameters(p: Parameters): Parameters =
      p.withMinSuccessfulTests(50)
       .withMaxDiscardRatio(5f)
       .withMinSize(10)
       .withMaxSize(100)
       .withWorkers(4)

  property("of is a correct factory") =
    forAll(smallInt(), arbitrary[String]): (i, s) =>
      of(i, s) == of(i, s).filter(e => e == s)
        &&
        forAll(smallInt(), arbitrary[String]): (i, s) =>
          of(i, s).filter(e => e != s) == Nil()
        &&
        forAll(smallInt(), arbitrary[String]): (i, s) =>
          Cons(s, of(i, s)) == of(i + 1, s)
        &&
        forAll(arbitrary[String]): s =>
          of(0, s) == Nil()

