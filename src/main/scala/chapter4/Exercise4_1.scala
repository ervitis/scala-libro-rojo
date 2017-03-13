package chapter4

import skel.Skel

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]

sealed trait Option[+A] {
  def map[B](f: A => B): Option[B] = this match {
    case None => None
    case Some(a) => Some(f(a))
  }

  def getOrElse[B>:A](default: => B): B = this match {
    case None => default
    case Some(a) => a
  }

  def flatMap[B](f: A => Option[B]): Option[B] = map(f) getOrElse None

  def orElse[B >: A](ob: => Option[B]): Option[B] = this map (Some(_)) getOrElse ob

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(a) if f(a) => this
    case _ => None
  }

  def mean(xs: Seq[Double]): Option[Double] = if (xs.isEmpty) None else Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] = mean(xs) flatMap (m => mean(xs.map(x => math.pow(x - m, 2))))
}

object MyOption {
  def option = new Option[String]{}
}

case class Employee(name: String, department: String)

class Exercise4_1 extends Skel {

  override def execute() = {
    val employeesByName: Map[String, Employee] = List(Employee("Alice", "R&D"), Employee("Bob", "Accounting")).map(e => (e.name, e)).toMap
    val anotherEmployee = Option(Employee("Jay", "Consultant engineering"))
    val department = employeesByName.get("Joe").orElse(anotherEmployee).map(_.department).get

    val filterDepartment = employeesByName.get("Boe").map(_.department).filter(v => v != "Nursing").getOrElse("Unkown")

    println(department)
    println(filterDepartment)

    val myVariance = MyOption.option.variance(Seq(2.0, 1.0, 4.0))
    println(myVariance.getOrElse("none"))
  }
}
