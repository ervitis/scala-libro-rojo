package chapter3

import interfaces.ScalaTestingBase

class Exercise3_2Test extends ScalaTestingBase {
  describe("TAD trees") {
    val data = Branch(Leaf(6), Branch(Leaf(2), Leaf(3)))

    it("if is a void tree return 0 in size") {
      assert(Tree.size(Leaf(0)) === 1)
    }

    it("if it has more elements return the size of it") {
      assert(Tree.size(data) === 5)
    }

    it("returns the max value in a tree") {
      assert(Tree.maximum(data) === 6)
    }

    it("returns the depth of the tree") {
      assert(Tree.depth(data) === 2)
    }

    it("returns the depth of the tree if the tree is empty") {
      assert(Tree.depth(Leaf(0)) === 0)
    }

    it("using map and a function returns a tree") {
      assert(Tree.map(data)((a: Int) => a + 6) === Branch(Leaf(12),Branch(Leaf(8),Leaf(9))))
    }
  }
}
