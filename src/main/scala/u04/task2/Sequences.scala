package scala.u04.task2
import scala.annotation.tailrec
import scala.u04.task2.Sequences.ScalaListSequenceADT

/*
type:
  Sequence[A]

constructors:
  cons: A x Sequence[A] => Sequence[A]
  nil: Sequence[A]

operations :
  filter[A]: Sequence[A] x (A => Boolean) => Sequence[A]
  map[A,B]: Sequence[A] x (A => B) => Sequence[B]
  concat[A]: Sequence[A] x Sequence[A] => Sequence[A]
  flatmap[A, B]: Sequence[A] x (A => Sequence[B]) => Sequence[B]
  foldLeft[A, B]: Sequence[A] x B x ((B, A) => B) => B
  reduce[A]: Sequence[A] x ((A, A) => A) => Option[A]

axioms :
  filter(nil, f) = nil
  filter(cons(h,t),f)) = if f(h) then cons(h, filter(t, f)) else filter(t, f)

  map(nil, f) = nil
  map (cons(h, t), f) = cons(f(h), map(t, f))

  concat(nil, s2) = s2
  concat(cons(h, t), s2) = cons(h, concat(t, s2))

  flatmap(nil, f) = nil
  flatmap(cons(h, t), f) = concat(f(h), flatmap(t, f))

  foldLeft(nil, z, _) = z
  foldLeft(cons(h, t), z, op) = foldLeft(t, op(z, h), op)

  reduce(nil, _) = nil
  reduce(s, op) = foldLeft(t, h, op)
 */

object Sequences:

  trait SequenceADT:
    type Sequence[A]
    def getCons[A](s: Sequence[A]): Option[(A, Sequence[A])]
    def cons[A](h: A, t: Sequence[A]): Sequence[A]
    def nil[A](): Sequence[A]
    def filter[A](sequence: Sequence[A], f: A => Boolean): Sequence[A]
    def map[A, B](sequence: Sequence[A], mapper: A=>B): Sequence[B]
    def concat[A](s1: Sequence[A], s2: Sequence[A]): Sequence[A]
    def flatMap[A, B](sequence: Sequence[A], f: A => Sequence[B]): Sequence[B]
    def foldLeft[A, B](sequence: Sequence[A], z: B, op: (B, A) => B): B
    def reduce[A](sequence: Sequence[A], op: (A, A) => A): Option[A]

  object BasicSequenceADT extends SequenceADT:
    private enum SequenceImpl[A]:
      case Cons(a: A, t: Sequence[A])
      case Nil()
    import SequenceImpl.*

    opaque type Sequence[A] = SequenceImpl[A]

    override def getCons[A](s: Sequence[A]): Option[(A, Sequence[A])] = s match
      case Cons(h, t) => Some((h, t))
      case _ => None

    override def cons[A](h: A, t: Sequence[A]): Sequence[A] = Cons(h, t)

    override def nil[A](): Sequence[A] = Nil()

    override def filter[A](sequence: Sequence[A], f: A => Boolean): Sequence[A] = sequence match
      case Cons(h, t) => if f(h) then Cons(h, filter(t, f)) else filter(t, f)
      case _ => Nil()

    override def map[A, B](sequence: Sequence[A], mapper: A => B): Sequence[B] = sequence match
      case Cons(h, t) => Cons(mapper(h), map(t, mapper))
      case _ => Nil()

    override def concat[A](s1: Sequence[A], s2: Sequence[A]): Sequence[A] = (s1, s2) match
      case (Nil(), s2) => s2
      case (Cons(h, t), s2) => Cons(h, concat(t, s2))

    override def flatMap[A, B](sequence: Sequence[A], f: A => Sequence[B]): Sequence[B] = sequence match
      case Cons(h, t) => concat(f(h), flatMap(t, f))
      case _ => Nil()

    @tailrec
    override def foldLeft[A, B](sequence: Sequence[A], z: B, op: (B, A) => B): B = sequence match
      case Cons(h, t) => foldLeft(t, op(z, h), op)
      case Nil() => z

    override def reduce[A](sequence: Sequence[A], op: (A, A) => A): Option[A] = sequence match
      case Cons(h, t) => Some(foldLeft(t, h, op))
      case Nil() => None
  
  object ScalaListSequenceADT extends SequenceADT:
    opaque type Sequence[A] = List[A]

    override def cons[A](h: A, t: Sequence[A]): Sequence[A] = h :: t

    override def nil[A](): Sequence[A] = List()

    override def getCons[A](s: Sequence[A]): Option[(A, Sequence[A])] = s match
      case h :: t => Some((h, t))
      case _ => None

    override def filter[A](sequence: Sequence[A], f: A => Boolean): Sequence[A] = sequence.filter(f)

    override def map[A, B](sequence: Sequence[A], mapper: A => B): Sequence[B] = sequence.map(mapper)

    override def concat[A](s1: Sequence[A], s2: Sequence[A]): Sequence[A] = s1 ++ s2

    override def flatMap[A, B](sequence: Sequence[A], f: A => Sequence[B]): Sequence[B] = sequence.flatMap(f)

    override def foldLeft[A, B](sequence: Sequence[A], z: B, op: (B, A) => B): B = sequence.foldLeft(z)(op)

    override def reduce[A](sequence: Sequence[A], op: (A, A) => A): Option[A] = sequence.reduceOption(op)






