package chapter2

import skel.Skel

class Exercise2 extends Skel {
  def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    val n = as.length - 1
    var iss = true

    for (i <- 0 until n) {
      if (! gt(as(i), as(i+1))) {
        iss = false
      }
    }
    iss
  }

  def isSortedRecursive[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
    val l = as.length - 1

    @annotation.tailrec
    def check(n: Int): Boolean = {
      if (n >= as.length - 1) true
      else if (gt(as(n), as(n+1))) false
      else check(n+1)
    }
    check(0)
  }

  def execute(): Unit = {
    val l = Array(1, 2, 3, 4)
    println(isSorted(l, (a: Int, b: Int) => (a < b)))

    val m = Array('a', 'b', 'c')
    println(isSorted(m, (a: Char, b: Char) => (a > b)))

    val n = Array('b', 'a', 'c')
    println(isSorted(n, (a: Char, b: Char) => (a < b)))

    val p = Array(1, 2, 3, 4)
    println(isSortedRecursive(p, (a: Int, b: Int) => (a < b)))
  }
}
