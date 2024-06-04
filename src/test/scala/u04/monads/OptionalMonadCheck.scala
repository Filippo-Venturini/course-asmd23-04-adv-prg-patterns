package scala.u04.monads

import u04.monads.{IOs, Optionals}
import org.scalacheck.{Gen, Properties}

import scala.u04.monads.SequenceCheck.property
import Optionals.Optional
import Optionals.Optional.{Empty, Just}
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.Prop.forAll

object OptionalMonadCheck extends Properties("OptionalMonads"):

  import Optionals.{*, given} // importing also given terms
  import Optional.* // importing Optional algorithms
  def fGenOptionals(): Gen[Double => Optional[Double]] = Gen.oneOf[Double => Optional[Double]](x => Just(x + 1), x => Just(x * 2), x => Just(x * x))

  // monadic laws :
  // Left identity : flatMap ( unit (a),f) === f(a)
  // Right identity : flatMap (m, unit (_)) === m
  // Associativity : flatMap ( flatMap (m,f),g) === flatMap (m,x=>flatMap (f(x),g))

  property("Left identity")=
    forAll(arbitrary[Double], fGenOptionals()): (a, f) =>
      Just(a).flatMap(f) == f(a)

  property("Right identity")=
    forAll(arbitrary[Double]): a =>
      Just(a).flatMap(x => Just(x)) == Just(a)

  property("Associativity")=
    forAll(arbitrary[Double], fGenOptionals(), fGenOptionals()): (a, f, g) =>
      Just(a).flatMap(f).flatMap(g) == Just(a).flatMap(x => f(x).flatMap(g))