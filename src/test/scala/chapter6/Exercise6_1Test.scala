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
    }
  }
}
