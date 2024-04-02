package scala.u04.monads

import u04.monads.Optionals
import org.scalacheck.Properties
import u04.monads.Monads.Monad

import scala.u04.monads.SequenceCheck.property

object MonadsCheck extends Properties("Monads"):

  import Optionals.{*, given} // importing also given terms
  import Optional.* // importing Optional algorithms

  val opt: Optional[Double] = Just(java.lang.Math.random()).filter(_ < 0.9)
  val f: Double => Optional[Double] = x => Just(x/2)
  //val optionalValue: Optionals.Optional[Int] = Monad[Optionals.Optional].unit(someValue)
/*
  property("Right identity")=
    Monad[Optional].unit(someValue)*/
