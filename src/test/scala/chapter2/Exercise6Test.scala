package chapter2

import interfaces.ScalaTestingBase

class Exercise6Test extends ScalaTestingBase {
  describe("compose functions") {
    val ex = new Exercise6()

    it("returns the identity if two functions are the opposite") {
      val f = (a: Double) => 1 / a
      val g = (a: Double) => a

      ex.compose(f, g)(1) shouldBe 1.0
    }

    it("if the same function composed two times should be the square of the function") {
      val f = (a: Double) => Math.pow(a, 2)

      ex.compose(f, f)(3) shouldBe 81.0
    }

    it("if we apply the composition of the inverse composition should return 1") {
      val f = (a: Double) => 2 * a
      val g = (a: Double) => a * a + 2 * a + a

      (Math.pow(ex.compose(f, g)(2), -1.0) * ex.compose(f, g)(2)) shouldBe 1
    }
  }
}
