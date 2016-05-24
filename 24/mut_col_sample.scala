import collection.mutable._

val buf = ArrayBuffer.empty[Int]

def measure( proc: => Any ) {
  println("measure start!")
  val start = System.currentTimeMillis
  proc 
  val resultTime = System.currentTimeMillis - start 
  println("mesure end")
  println(resultTime + "msec")
}

measure {
  for(x <- 1 to 10000) buf += x
  buf.toArray
}

var array = Array[Int]()
measure {
  for(x <- 1 until  10000) array :+= x
}

var s = ""
val length = 10000
measure {
  for(_ <- 1 to length)  s += 'a'
}

val s = new StringBuilder
measure {
  for(_ <- 1 to length ) s += 'a'
  s.toString
}
