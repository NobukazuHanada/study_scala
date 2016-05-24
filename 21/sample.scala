package com.nobkz

object Hanada {
  sealed abstract class ConsList[+T]
  case object Nil extends ConsList[Nothing]
  case class Cons[T](a: T, rest : ConsList[T]) extends ConsList[T]

  implicit def consListToList[T](c: ConsList[T]) : List[T] =
    c match {
      case Nil => List()
      case Cons(a, rest) => a :: consListToList(rest)
    }
}
