import scala.beans.BeanProperty
import scala.annotation._

object Mistaker{
  @deprecated("sample","2.1.1") def bigMistake() = null
  @BeanProperty
  var x = 10

  @tailrec def reverse[T](a:List[T],acc:List[T]):List[T] = 
    a match {
      case List() => acc
      case a :: b => reverse(b, a :: acc)
    }


}



object Main extends App{
  Mistaker.bigMistake()
  println(Mistaker.x)
  Mistaker.setX(20)
  println(Mistaker.getX)
}
