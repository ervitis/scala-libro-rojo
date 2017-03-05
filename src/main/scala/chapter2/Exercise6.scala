package chapter2

import skel.Skel

class Exercise6 extends Skel {
  def compose[A, B, C](f: B => C, g: A => B): A => C = { a =>
    f(g(a))
  }

  override def execute() = {
    val f = (a: Double) => a / 2
    val g = (a: Double) => 2 * a

    println(compose(f, g)(4))
  }
}
