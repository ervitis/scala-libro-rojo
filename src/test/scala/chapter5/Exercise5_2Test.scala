package chapter5

import interfaces.ScalaTestingBase

class Exercise5_2Test extends ScalaTestingBase {
  describe("streams") {
    val s = Stream(2, 3, 4, 5, 6, 1, 2, 3)

    it("returns a list") {
      assert(s.toList === List(2, 3, 4, 5, 6, 1, 2, 3))
    }

    it("returns an empty list") {
      assert(Stream().toList === List())
    }

    it("take function") {
      val ns = s.take(3).toList

      assert(ns === List(2, 3, 4))
    }

    it("takewhile") {
      assert(s.takeWhile(_ < 4).toList === List(2, 3))
    }

    it("forAll") {
      assert(s.forAll(_ % 7 != 0))  // it doesn't have any element multiple of 7
      assert(Stream(2, 4, 6).forAll(_ % 2 === 0))
    }

    it("map") {
      assert(s.map(_ + 2).toList === List(4, 5, 6, 7, 8, 3, 4, 5))
    }

    it("filter") {
      assert(s.filter(_ % 2 === 0).toList === List(2, 4, 6, 2))
    }
  }
}
