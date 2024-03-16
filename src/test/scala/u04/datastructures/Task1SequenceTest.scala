package scala.u04.datastructures

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Inspectors.forAll
import org.scalatest.funsuite.AnyFunSuite
import u04.datastructures.Sequences.*
import Sequence.*
import org.scalatest.matchers.should.Matchers.shouldBe

import scala.util.Random

class Task1SequenceTest extends AnyFunSuite with BeforeAndAfterAll:
  val rand : Random = Random()
  val randomValues: List[(Int, String)] = List()
  val numberOfTests = 100
  val stringLength = 10

  override def beforeAll(): Unit =
    for (_ <- 0 to numberOfTests) do
      randomValues :+ (rand.nextInt(), rand.nextString(stringLength))


  test("of is a correct factory"):
    forAll(randomValues): (i, s) =>
      of(i, s) shouldBe of(i, s).filter(e => e == s)

    forAll(randomValues): (i, s) =>
      of(i, s).filter(e => e != s) shouldBe Nil()

    forAll(randomValues): (i, s) =>
      Cons(s, of(i, s)) shouldBe of(i + 1, s)

    forAll(randomValues.map((i, s) => s)): s =>
      of(0, s) shouldBe Nil()

