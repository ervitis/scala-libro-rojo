package chapter2

import interfaces.ScalaTestingBase

class Exercise4Test extends ScalaTestingBase {
  describe("A currying function that calculates the exponential in base 2") {
    val ex = new Exercise4()

    val f = ex.curry((b: Double, exp: Int) => Math.pow(b, exp))(2)

    f(3) shouldBe 8
    f(-1) shouldBe 0.5
  }
}
