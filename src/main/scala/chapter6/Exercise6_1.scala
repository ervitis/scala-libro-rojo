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

case class State[S, +A](run: S => (A, S)) {
  import State._

  def map[B](f: A => B): State[S, B] = flatMap(a => unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] = flatMap(a => sb.map(b => f(a, b)))

  def flatMap[B](f: A => State[S, B]): State[S, B] = State(s => {
    val (a, s1) = run(s)
    f(a).run(s1)
  })

  def get[S]: State[S, S] = State(s => (s, s))
}

object State {
  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[S, A](a: A): State[S, A] = State(s => (a, s))

  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] = {
    def go(s: S, actions: List[State[S, A]], acc: List[A]): (List[A], S) = actions match {
      case Nil => (acc.reverse, s)
      case h :: t => h.run(s) match {
        case (a, s2) => go(s2, t, a :: acc)
      }
    }
    State((s: S) => go(s, sas, List()))
  }

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get
    _ <- set(f(s))
  } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

  def flatMap[A, B](f: Rand[A])(g: A => Rand[B]): Rand[B] = rng => {
    val (a, rng1) = f(rng)
    g(a)(rng1)
  }

}

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object CandyMachine {
  import State._

  def update = (i: Input) => (s: Machine) => (i, s) match {
    case (_, Machine(_, 0, _)) => s
    case (Coin, Machine(false, _, _)) => s
    case (Coin, Machine(true, _, _)) => s
    case (Coin, Machine(true, candy, coin)) => Machine(locked = false, candy, coin + 1)
    case (Coin, Machine(false, candy, coin)) => Machine(locked = true, candy - 1, coin)
  }

  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = for {
    _ <- sequence(inputs map (modify[Machine] _ compose update))
    s <- get
  } yield (s.coins, s.candies)
}

class Exercise6_1 extends Skel {
  def execute(): Unit = {

  }
}
