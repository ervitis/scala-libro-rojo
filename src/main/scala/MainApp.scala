import chapter1.Exercise1
import skel.Skel

object MainApp {
  def getExercises: List[Skel] = {
    val exercises = List(new Exercise1())

    exercises
  }

  def main(args: Array[String]): Unit = {
    val exercises = getExercises

    exercises.map(e => e.execute())
  }
}
