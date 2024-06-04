package scala.u04.monads

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties, Arbitrary}
import org.scalacheck.Arbitrary.arbitrary

object IOMonadCheck extends Properties("IOMonads"):

  import u04.monads.IOs.{*, given}, IO.*

  def fGenIOs(): Gen[String => IO[String]] = Gen.oneOf[String => IO[String]](s => IO(() => ""), s => IO(() => "abc"), s => IO(() => "z"))

  // monadic laws :
  // Left identity : flatMap ( unit (a),f) === f(a)
  // Right identity : flatMap (m, unit (_)) === m
  // Associativity : flatMap ( flatMap (m,f),g) === flatMap (m,x=>flatMap (f(x),g))
  
  property("Left identity IO") =
    forAll(arbitrary[String], fGenIOs()): (a, f) =>
      IO(() => a).flatMap(f) == f(a)

  property("Right identity") =
    forAll(arbitrary[String]): a =>
      IO(() => a).flatMap(x => IO(() => x)) == IO(() => a)

  property("Associativity") =
    forAll(arbitrary[String], fGenIOs(), fGenIOs()): (a, f, g) =>
      IO(() => a).flatMap(f).flatMap(g) == IO(() => a).flatMap(x => f(x).flatMap(g))


