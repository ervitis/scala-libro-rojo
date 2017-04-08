package chapter7

import java.util.concurrent.Callable

import scala.concurrent.duration.TimeUnit


abstract class ExecutorService {
  def submit[A](a: Callable[A]): Future[A]
}

trait Future[A] {
  def get: A

  def get(timeout: Long, unit: TimeUnit)

  def cancel(evenIfRunning: Boolean): Boolean

  def isDone: Boolean

  def isCancelled: Boolean
}

case class UnitFuture[A](get: A) extends Future[A] {
  override def get(timeout: Long, unit: TimeUnit): Unit = get

  override def isDone: Boolean = true

  override def isCancelled: Boolean = false

  override def cancel(evenIfRunning: Boolean): Boolean = false
}

object Par {

  type Par[A] = ExecutorService => Future[A]

  def run[A](s: ExecutorService)(a: Par[A]): Future[A] = a(s)

  def unit[A](a: A): Par[A] = (s: ExecutorService) => UnitFuture(a)

  def map2[A, B, C](a: Par[A], b: Par[B])(f: (A, B) => C): Par[C] = (s: ExecutorService) => {
    val a2 = a(s)
    val b2 = b(s)
    UnitFuture(f(a2.get, b2.get))
  }

  def fork[A](a: => Par[A]): Par[A] = s => s.submit(new Callable[A] {
    def call(): A = a(s).get
  })

  def asyncF[A, B](f: A => B): A => Par[B] = a => fork(unit(f(a)))  // fork a future of function

  def sortPar(l: Par[List[Int]]): Par[List[Int]] = map2(l, unit())((a, _) => a.sorted)

  def map[A, B](fa: Par[A])(f: A => B): Par[B] = map2(fa, unit(()))((a, _) => f(a))

  def sequence[A](as: List[Par[A]]): Par[List[A]] = as match {
    case Nil => unit(Nil)
    case h :: t => map2(h, sequence(t))(_ :: _)
  }

  def sortPar2(l: Par[List[Int]]): Par[List[Int]] = map(l)(_.sorted)

  def parMap[A, B](l: List[A])(f: A => B): Par[List[B]] = fork {
    sequence(l.map(asyncF(f)))
  }

  def parFilter[A](l: List[A])(f: A => Boolean): Par[List[A]] = l match {
    case Nil => unit(Nil)
    case h :: t => map2(unit(f(h)), parFilter(t)(f)) { (m, r) =>
      if (m) h :: r
      else r
    }
  }

  def equal[A](e: ExecutorService)(p: Par[A], p2: Par[A]): Boolean = p(e).get == p2(e).get

  def delay[A](f: => Par[A]): Par[A] = es => f(es)

}

class Exercise7_1 {

}
