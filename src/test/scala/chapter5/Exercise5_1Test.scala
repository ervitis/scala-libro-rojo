package chapter5

import interfaces.ScalaTestingBase

class Exercise5_1Test extends ScalaTestingBase {
  describe("if conditional") {
    val ex = new Exercise5_1()

    it("returns onTrue sentence") {
      assert(ex.if2(4 < 8, true, false) === true)
    }

    it("returns onFalse sentence") {
      assert(ex.if2(2 === 1, true, false) === false)
    }
  }
}
