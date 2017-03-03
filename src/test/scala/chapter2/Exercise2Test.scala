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

  describe("Use the recursive function") {
    val ex = new Exercise2()

    it("sorted") {
      assert(ex.isSortedRecursive(Array(1, 2, 3, 4), (a: Int, b: Int) => (a > b)) === true)
    }

    it("not sorted") {
      assert(ex.isSortedRecursive(Array(1, 5, 2, 3), (a: Int, b: Int) => (a > b)) === false)
    }
  }
}
