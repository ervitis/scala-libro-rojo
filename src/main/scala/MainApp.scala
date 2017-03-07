import chapter1.Exercise1
import skel.Skel
import chapter2.{Exercise2, Exercise3, Exercise4, Exercise5, Exercise6, Exercise1 => Exercise11}
import chapter3.Exercise3_1

object MainApp {
  def getExercises: List[Skel] = {
    val chapter1 = List(new Exercise1())
    val chapter2 = List(new Exercise11(), new Exercise2(), new Exercise3(), new Exercise4(), new Exercise5(), new Exercise6())
    val chapter3 = List(new Exercise3_1())

    chapter1 ++ chapter2 ++ chapter3
  }

  def main(args: Array[String]): Unit = {
    val exercises = getExercises

    exercises.map(e => e.execute())
  }
}
