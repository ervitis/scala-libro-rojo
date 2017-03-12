package chapter3

import interfaces.ScalaTestingBase

class Exercise3_1Test extends ScalaTestingBase {
  describe("using our data structure of a list") {
    val listData = Lista(4, 5, 3, 2, 8)

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

    it("returns the first element") {
      val l = Cons(2, Cons(5, Cons(8, Cons(2, Cons(9, Nil)))))

      assert(Lista.tail(l) === 2)
    }

    it("returns a list of n elements") {
      val l = Cons(2, Cons(5, Cons(8, Cons(2, Cons(9, Nil)))))

      assert(Lista.drop(l, 3) === Cons(2, Cons(9, Nil)))
    }

    it("returns a list of n elements while it has a condition") {
      val l = Lista(1, 2, 3, 8, 4, 3)

      val r = Lista.dropWhile(l, (x: Int) => x < 8)
      assert(r === Cons(8,Cons(4,Cons(3,Nil))))
    }

    it("changes the head of the list") {
      val l = Lista(1, 2, 3, 4)

      assert(Lista.setHead(l, 4) === Lista(4, 2, 3, 4))
      assert(Lista.setHead(Nil, 3) === Lista(3))
    }

    it("calculates the length of a list") {
      assert(Lista.length(listData) === 5)
    }

    it("calculates foldleft") {
      assert(Lista.foldLeft(listData, 0)(_ + _) === 22)
    }

    it("reverses a list") {
      assert(Lista.reverse(listData) === Lista(8, 2, 3, 5, 4))
    }

  }
}
