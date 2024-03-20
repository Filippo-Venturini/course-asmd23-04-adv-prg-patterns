package scala.u04.task2

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}

object SequenceCheck extends Properties("Sequence"):

  import scala.u04.task2.Sequences.BasicSequenceADT.*
  def sequenceGen(): Gen[Sequence[Int]] = for
    size <- Gen.choose(0, 100) // Use the Gen.choose method to determine the size of the sequence
    elements <- Gen.listOfN(size, Gen.choose(0, 100)) // Generate a list of random integers
  yield elements.foldRight[Sequence[Int]](nil())((e, acc) => cons(e, acc)) // Convert the list to a sequence

  //Pick a random mapper between the three
  def mapperGen(): Gen[Int => Int] = Gen.oneOf[Int => Int](_ + 1, _ * 2, x => x * x)

  /*
  property("map axiom") =
    forAll(sequenceGen(), mapperGen()): (s, m) =>
      (s, m) match
      case(nil, _) => map(s, m) == nil
      case(cons(h,t), m) => map(s, m) == cons(m(h), map(t, m))*/


