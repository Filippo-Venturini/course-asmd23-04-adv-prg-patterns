package scala.u04.task2

/*
type:
  Sequence[A]

constructors:
  cons: A x Sequence[A] => Sequence[A]
  nil: Sequence[A]

operations :
  append[A]: Sequence[A] x Sequence[A] => Sequence[A]
  map[A, B]: Sequence[A] x (A => B) => Sequence[B]
  filter[A]: Sequence[A] x (A => Boolean) => Sequence[A]

axioms :
  append(s1, nil) = s1
  append(nil, s2) = s2
  append(cons(h, t), s2) =

  map(nil, f) = nil
  map (cons(h, t), f) = cons(f(h), map(t, f))


 */

object SequenceADT:
  enum Sequence[A]:
    case Cons(head: A, tail: Sequence[A])
    case Nil()

  object Sequence:
    def append[A](seq1: Sequence[A], seq2: Sequence[A]): Sequence[A] = seq1 match
      case Cons(h, t) => Cons(h, append(t, seq2))
      case _ => seq2

    def map[A, B](sequence: Sequence[A])(mapper: A => B): Sequence[B] = sequence match
      case Cons(h, t) => Cons(mapper(h), map(t)(mapper))
      case _ => Nil()

    def filter[A](sequence: Sequence[A])(f: A => Boolean): Sequence[A] = sequence match
      case Cons(h, t) => if f(h) then Cons(h, filter(t)(f)) else filter(t)(f)
      case _ => Nil()

    def flatMap[A, B](sequence: Sequence[A])(mapper: A => Sequence[B]): Sequence[B] = sequence match
      case Cons(h, t) => append(mapper(h), flatMap(t)(mapper))
      case _ => Nil()



