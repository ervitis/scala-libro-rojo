package chapter3

import skel.Skel

sealed trait List[+A]

case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object Lista {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

class Exercise3_1 extends Skel {
  override def execute() = {
    val l = Cons(1, Cons(2, Cons(3, Nil)))
    val m = Lista(1.0, 2.0, 3.0)

    println(Lista.sum(l))
    println(Lista.product(m))
  }
}
