package chapter1

import org.scalatest.{FunSpec, Matchers}

class Exercise1Test extends FunSpec with Matchers {

  describe("Exercise1 tests") {
    val exercise1 = new Exercise1()

    it("winner should return the winner player") {
      val p1 = Player("Norman", 50)
      val p2 = Player("Melanie", 51)

      assert(exercise1.winner(p1, p2) === p2)
    }

    it("getPlayers should return a list of players") {
      val players = exercise1.getPlayers

      assert(players.isInstanceOf[List[Player]])
    }
  }
}
