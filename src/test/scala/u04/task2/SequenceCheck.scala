package scala.u04.task2

import org.scalacheck.Prop.forAll
import org.scalacheck.{Arbitrary, Gen, Properties}
import org.scalatest.Assertions.intercept

object SequenceCheck extends Properties("Sequence"):

  import scala.u04.task2.Sequences.*

  sequencesTestAxioms(BasicSequenceADT)
  sequencesTestAxioms(ScalaListSequenceADT)

  def sequencesTestAxioms(sequenceADT: SequenceADT): Unit =
    import sequenceADT.*

    def sequenceGen(): Gen[Sequence[Int]] = for
      b <- Gen.prob(0.7)
      size <- Gen.choose(0, 0) // Use the Gen.choose method to determine the size of the sequence
      elements <- Gen.listOfN(size, Gen.choose(0, 100)) // Generate a list of random integers
    yield if b then nil() else elements.foldRight[Sequence[Int]](nil())((e, acc) => cons(e, acc)) // Convert the list to a sequence

    def intGen(): Gen[Int] = Gen.choose(0, 100)

    def mapperGen(): Gen[Int => Int] = Gen.oneOf[Int => Int](_ + 1, _ * 2, x => x * x)

    def filterGen(): Gen[Int => Boolean] = Gen.oneOf[Int => Boolean](_ % 2 == 0, _ % 2 != 0, _ >= 10)

    def flatMapGen(): Gen[Int => Sequence[String]] = Gen.oneOf[Int => Sequence[String]](_ => cons("a", cons("b", nil())), _ => cons("dummy", nil()), _ => cons("x", cons("z", cons("c", nil()))))

    def operatorGen(): Gen[(Int, Int) => Int] = Gen.oneOf[(Int, Int) => Int]((x, y) => x + y, (x, y) => x - y, (x, y) => x * y)

    property("filter axiom") =
      forAll(sequenceGen(), filterGen()): (s, f) =>
        (s, f) match
          case (nil, _) => filter(nil, f) == nil
          //case (cons(h, t), f) => (f(h) && filter(s, f) == cons(h, filter(t, f)) || (!f(h) && filter(s, f) == filter(t))

    property("map axiom") =
      forAll(sequenceGen(), mapperGen()): (s, m) =>
        (s, m) match
        case(nil, _) => map(s, m) == nil
        //case(cons(h,t), m) => map(s, m) == cons(m(h), map(t, m))

    property("concat axiom") =
      forAll(sequenceGen(), sequenceGen()): (s1, s2) =>
        (s1, s2) match
          case (s1, nil) => concat(s1, s2) == s1
          case (nil, s2) => concat(s1, s2) == s2
          //case (cons(h, t), s2) => concat(s1,s2) == cons(h, concat(t, s2))

    property("flatmap axiom") =
      forAll(sequenceGen(), flatMapGen()): (s, f) =>
        (s, f) match
          case (nil, _) => flatMap(s, f) == nil
          //case (cons(h, t), f) => flatMap(s, f) == concat(f(h), flatMap(t, f))

    property("foldLeft axiom") =
      forAll(sequenceGen(), intGen(), operatorGen()): (s, z, op) =>
        (s, z, op) match
          case (nil, z, _) => foldLeft(s, z, op) == z
          //case (cons(h, t), z, op) => foldLeft(s, z, op) == foldLeft(t, op(z, h), op)

    property("reduce axiom") =
      forAll(sequenceGen(), operatorGen()): (s, op) =>
        (s, op) match
          //case (cons(h, t), op) => reduce(s, op) == foldLeft(t, h, op)
          case (nil, _) => intercept[UnsupportedOperationException]{ reduce(s, op) } == UnsupportedOperationException()
