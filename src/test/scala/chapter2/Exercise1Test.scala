package chapter2

import org.scalatest.{FunSpec, Matchers}

class Exercise1Test extends FunSpec with Matchers {
  val factorials = new Exercise1()

  describe("factorials") {
    it("factorialWithTail") {
      assert(factorials.factorialWithTail(3) === 6)
    }

    it("fibonacci") {
      assert(factorials.fib(10) === 55)
    }

    it("fibonacci with tail recursion") {
      assert(factorials.fib_tail(10) === 55)
    }
  }

}
