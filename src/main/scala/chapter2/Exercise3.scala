package chapter2

import skel.Skel

class Exercise3 extends Skel {
  def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
    (b: B) => f(a, b)
  }

  override def execute() = {
    println(partial1(4, (a: Int, b: Int) => a + b)(2))
  }
}
