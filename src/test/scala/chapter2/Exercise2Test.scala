package chapter2

import org.scalatest.{FunSpec, Matchers}

class Exercise2Test extends FunSpec with Matchers {
  describe("Given a function gt tell me if an array is") {
    val ex = new Exercise2()

    it("sorted") {
      assert(ex.isSorted(Array(4, 3, 2, 1), (a: Int, b: Int) => (a > b)) === true)
    }

    it("not sorted") {
      assert(ex.isSorted(Array('b', 'z', 'a'), (a: Char, b: Char) => (a > b)) === false)
    }
  }
}
