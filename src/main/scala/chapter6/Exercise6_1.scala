package chapter6

import skel.Skel

trait RNG {
  def nextInt: (Int, RNG)
}

object RNG {
  def simple(seed: Long): RNG = new RNG {
    def nextInt: (Int, RNG) = {
      val seed2 = (seed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1)
      ((seed2 >>> 16).asInstanceOf[Int], simple(seed2))
    }
  }

  def positiveInt(rng: RNG): (Int, RNG) = {
    val (v, r) = rng.nextInt
    (v.abs, r)
  }

  def double(rng: RNG): (Double, RNG) = {
    val (v, r) = positiveInt(rng)
    var d: Double = v.toDouble
    if (v >= 1) d /= Int.MaxValue
    (d, r)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (v, _) = positiveInt(rng)
    val (d, r) = double(rng)
    ((v, d), r)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val v = intDouble(rng)
    ((v._1._2, v._1._1), v._2)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) =
    ((double(rng)._1, double(rng)._1, double(rng)._1), double(rng)._2)

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    if (count <= 0) (List(), rng)
    else {
      val (x, r1) = positiveInt(rng)
      val (xs, r2) = ints(count - 1)(r1)
      (x :: xs, r2)
    }
  }
}

class Exercise6_1 extends Skel {
  def execute(): Unit = {

  }
}
