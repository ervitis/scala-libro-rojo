package chapter6

import interfaces.ScalaTestingBase

class Exercise6_1Test extends ScalaTestingBase {
  describe("RNG states") {

    it("returns a random int positive") {
      var r: Int = 0

      for(i <- 1 to 1000) {
        val rng = RNG.simple(i)
        r = RNG.positiveInt(rng)._1
        print(s"Testing loop $i with value $r; ")
        assert(r >= 0)
      }
      println()
    }

    it("returns a random double between 0 and 1") {
      var r: Double = 0.0

      for(i <- 1 to 1000) {
        val rng = RNG.simple(i)
        r = RNG.double(rng)._1
        print(s"Testing loop $i with value $r; ")
        assert(r >= 0 && r < 1)
      }
      println()
    }

    it("returns a tuple of int and double") {
      for(i <- 1 to 1000) {
        val rng = RNG.simple(i)
        val r = RNG.intDouble(rng)._1
        assert(r._1 >= 0)
        assert(r._2 >= 0 && r._2 < 1)
      }
    }

    it("returns a tuple of double and int") {
      for(i <- 1 to 1000) {
        val rng = RNG.simple(i)
        val r = RNG.doubleInt(rng)._1
        assert(r._2 >= 0)
        assert(r._1 >= 0 && r._1 < 1)
      }
    }

    it("returns a tuple of 3 doubles") {
      for(i <- 1 to 1000) {
        val rng = RNG.simple(i)
        val r = RNG.double3(rng)._1
        assert(r._1 >= 0 && r._1 < 1)
        assert(r._1 === r._2 && r._1 === r._3)
      }
    }

    it("returns a list of ints") {
      val l = RNG.ints(4)(RNG.simple(1L))
      assert(l._1.length === 4)
      l._1.foreach(v => assert(v >= 0))
    }
  }
}
