package chapter2

import org.scalatest.{FunSpec, Matchers}

class Exercise3Test extends FunSpec with Matchers {
  describe("partial functions") {
    it("we calculate a new function to do the double of a number") {
      val ex = new Exercise3()

      val f = ex.partial1(2.0, (a: Double, b: Double) => a * b)

      assert(f(3) === 6.0)
    }
  }

}
