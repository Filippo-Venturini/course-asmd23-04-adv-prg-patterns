package scala.u04.monads

import u04.monads.Optionals
import org.scalacheck.{Gen, Properties}

import scala.u04.monads.SequenceCheck.property
import Optionals.Optional
import Optionals.Optional.{Empty, Just}
import org.scalacheck.Prop.forAll

object MonadsCheck extends Properties("Monads"):

  import Optionals.{*, given} // importing also given terms
  import Optional.* // importing Optional algorithms

  def doubleGen(): Gen[Double] = Gen.choose(0, 100)
  def fGen(): Gen[Double => Optional[Double]] = Gen.oneOf[Double => Optional[Double]](x => Just(x + 1), x => Just(x * 2), x => Just(x * x))

  // monadic laws :
  // Left identity : flatMap ( unit (a),f) === f(a)
  // Right identity : flatMap (m, unit (_)) === m
  // Associativity : flatMap ( flatMap (m,f),g) === flatMap (m,x=>flatMap (f(x),g))
  
  property("Left identity")=
    forAll(doubleGen(), fGen()): (a, f) =>
      Just(a).flatMap(f) == f(a)

  property("Right identity")=
    forAll(doubleGen()): a =>
      Just(a).flatMap(x => Just(x)) == Just(a)

  property("Associativity")=
    forAll(doubleGen(), fGen(), fGen()): (a, f, g) =>
      Just(a).flatMap(f).flatMap(g) == Just(a).flatMap(x => f(x).flatMap(g))
