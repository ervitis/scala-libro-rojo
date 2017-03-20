package chapter5

import skel.Skel

class Exercise5_1 extends Skel {
  def if2[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
    if (cond) onTrue
    else onFalse

  override def execute() = {

  }
}
