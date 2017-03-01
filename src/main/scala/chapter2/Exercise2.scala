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

  def execute(): Unit = {
    val l = Array(1, 2, 3, 4)
    println(isSorted(l, (a: Int, b: Int) => (a < b)))

    val m = Array('a', 'b', 'c')
    println(isSorted(m, (a: Char, b: Char) => (a > b)))

    val n = Array('b', 'a', 'c')
    println(isSorted(n, (a: Char, b: Char) => (a < b)))
  }
}
