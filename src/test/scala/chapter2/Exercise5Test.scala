package chapter2

import interfaces.ScalaTestingBase

class Exercise5Test extends ScalaTestingBase {
  describe("uncurrying a function") {
    val f: Int => Int => Double = a => b => Math.pow(a, 2) + 2 * a * b + Math.pow(b, 2)
    val ex = new Exercise5()

    val g = ex.uncurry(f)

    g(1, 1) shouldBe 4
    g(1, -1) shouldBe 0
  }
}
