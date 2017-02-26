package chapter1

import skel.Skel

case class Player(name: String, score: Int)

class Exercise1 extends Skel {
  def printWinner(p: Player): Unit = println(p.name + " is the winner!")

  def declareWinner(p1: Player, p2: Player): Unit =
    if (p1.score > p2.score) printWinner(p1)
    else printWinner(p2)

  def winner(p1: Player, p2: Player): Player = if (p1.score < p2.score) p2 else p1

  def getPlayers = List(Player("Charles", 30), Player("Deborah", 23), Player("Edward", 46), Player("Faiz", 67))

  override def execute() = {
    val player1 = Player("Abel", 45)
    val player2 = Player("Barbara", 50)

    declareWinner(player1, player2)

    printWinner(winner(player1, player2))

    val player = getPlayers.reduceLeft(winner)
    printWinner(player)

    printWinner(getPlayers.reduceRight(winner))
  }
}
