def maxListUpBound[T <: Ordered[T]](elements: List[T]) : T =
  elements match {
    case List() =>
      throw new IllegalArgumentException("empty list!")
    case List(x) => x
    case x :: rest =>
      val maxRest = maxListUpBound(rest)
      if(x > maxRest) x
      else maxRest
  }

def maxListImpParm[T](elements: List[T])
  (implicit ordered: T => Ordered[T]) : T =
  elements match {
    case List() =>
      throw new IllegalArgumentException("empty list!")
    case List(x) => x
    case x :: rest =>
      val maxRest = maxListImpParm(rest)(ordered)
      if( ordered(x) > maxRest ) x
      else maxRest
  }

class PreferredPrompt(val preference: String)

object Greeter {
  def greet(name: String)(implicit prompt: PreferredPrompt) {
    println("Welcome" + name + "The system is ready")
    println(prompt.preference)
  }
}

object JosePrefs {
  implicit val p = new PreferredPrompt("Yes master1>")
}

def maxList[T <% Ordered[T] ](elements: List[T]) : T =
  elements match {
    case List() =>
      throw new IllegalArgumentException("empty list!")
    case List(x) => x
    case x :: rest =>
      val maxRest = maxList(rest)
      if( x > maxRest ) x
      else maxRest
  }
