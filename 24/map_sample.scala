val map = Map(
  "orange" -> 100,
  "apple" -> 200,
  "grape" -> 120,
  "potato" -> 50
)

println(map get "orange")
println(map get "banana")
println(map getOrElse ("apple",100))
println(map getOrElse ("banana",100))
println(map contains "orange")
println(map contains "banana")

val cache = collection.mutable.Map[String,String]()
def f(s:String) : String = {
  println("taking my time.")
  Thread.sleep(100)
  s.reverse
}

def cachedF(s:String):String = cache.getOrElseUpdate(s, f(s))
cachedF("10")
cachedF("10")

import collection.mutable.{Map, SynchronizedMap, HashMap}

object MapMaker{
  def makeMap: Map[String, String] = {
    new HashMap[String, String] with
        SynchronizedMap[String, String] {
      override def default(key: String) =
        "Why do you want to know?"
    }
  }
}

val capital = MapMaker.makeMap
capital ++= List("US" -> "Washington",
  "France" -> "Paris", "Japan" -> "Tokyo")
println(capital("Japan"))
println(capital("New Zealand"))
capital += ("New Zealand" -> "Wellington")
capital("New Zealand")

