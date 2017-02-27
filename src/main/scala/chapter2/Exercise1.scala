package chapter2

import skel.Skel

class Exercise1 extends Skel {
  def factorialWithTail(n: Int): Int = {
    @annotation.tailrec
    def go(n: Int, acc: Int): Int = {
      if (n <= 0) acc
      else go(n-1, n*acc)
    }
    go(n, 1)
  }

  def fib(n: Int): Int = {
    if (n == 0 || n == 1) n
    else fib(n-1) + fib(n-2)
  }

  def fib_tail(n: Int): Int = {
    def fib(n: Int, a: Int, b: Int): Int = {
      if (n == 0) a
      else fib(n-1, b, a+b)
    }
    fib(n, 0, 1)
  }

  override def execute() = {
    println(factorialWithTail(4))
    println(fib(5))
    println(fib_tail(5))
  }
}
