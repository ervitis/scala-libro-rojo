import chapter1.Exercise1
import skel.Skel
import chapter2.{Exercise2, Exercise1 => Exercise11}

object MainApp {
  def getExercises: List[Skel] = {
    val exercises = List(new Exercise1(), new Exercise11(), new Exercise2())

    exercises
  }

  def main(args: Array[String]): Unit = {
    val exercises = getExercises

    exercises.map(e => e.execute())
  }
}
