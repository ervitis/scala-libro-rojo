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

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def sum2(l: List[Int]) = foldRight(l, 0)(_ + _)

  def product2(l: List[Double]) = foldRight(l, 1.0)(_ * _)

  def product3(l: List[Double]) = l match {
    case Cons(0.0, _) => Cons(0.0, l)
    case _ => foldRight(l, 1.0)(_ * _)
  }

  def length[A](l: List[A]): Int = foldRight(l, 0)((_, i) => i + 1)

  @annotation.tailrec
  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = l match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def reverse[A](l: List[A]): List[A] = foldLeft(l, Lista[A]())((acc, h) => Cons(h, acc))

  def appendFoldRight[A](l: List[A], x: List[A]): List[A] = foldRight(l, x)(Cons(_, _))

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
