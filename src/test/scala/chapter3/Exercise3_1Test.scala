package chapter3

import interfaces.ScalaTestingBase

class Exercise3_1Test extends ScalaTestingBase {
  describe("using our data structure of a list") {
    it("should create a Nil object, so it will be an empty list") {
      val myNil = Nil

      assert(myNil === Lista())
    }

    it("should create a constant value using Nil") {
      val myCons = Cons(Nil, Nil)

      assert(myCons.head === Nil)
      assert(myCons.tail === Nil)
    }

    it("I can do the sum from the elements of the list") {
      val l = Cons(4, Cons(2, Nil))

      assert(Lista.sum(l) === 6)
    }

    it("I can do the product from the elements of the list") {
      val l = Cons(2.0, Cons(8.0, Nil))

      assert(Lista.product(l) === 16)
    }

  }
}
