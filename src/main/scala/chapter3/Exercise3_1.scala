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

  def tail[A](xs: List[A]): A = xs match {
    case Nil => throw new Exception("List null")
    case Cons(x, _) => x
  }

  def drop[A](xs: List[A], n: Int): List[A] = {
    if (n <= 0) xs
    else xs match {
      case Nil => Nil
      case Cons(_, l) => drop(l, n-1)
    }
  }

  def dropWhile[A](xs: List[A], f: A => Boolean): List[A] = xs match {
    case Cons(h, l) if f(h) => dropWhile(l, f)
    case _ => xs
  }

  def append[A](xs1: List[A], xs2: List[A]): List[A] = {
    xs1 match {
      case Nil => xs2
      case Cons(n, l) => Cons(n, append(l, xs2))
    }
  }

  def setHead[A](xs: List[A], n: A): List[A] = {
    xs match {
      case Cons(h, l) => Cons(n, l)
      case Nil => Cons(n, Nil)
    }
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
    println(Lista.drop(m, 2))
  }
}
