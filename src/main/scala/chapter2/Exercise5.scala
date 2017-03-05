package chapter2

import skel.Skel

class Exercise5 extends Skel {
  def uncurry[A, B, C](f: A => (B => C)): (A, B) => C = {
    (a, b) => f(a)(b)
  }

  override def execute() = {
    val f: Int => Int => Int = a => b => a + b
    val g = uncurry(f)

    println(g(4, 3))
  }
}
