package chapter2

import skel.Skel

class Exercise4 extends Skel {
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    a => b => f(a, b)
  }

  override def execute() = {
    val f = curry((a: Int, b: Int) => (a+b))
    println(f(4)(5))

    val g = curry((a: Double, b: Double) => (a*b))(2)
    println(g(5))
  }
}
