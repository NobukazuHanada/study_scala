val a1 = Array(1,2,3)
val a2 = a1 map (_ * 3)
val a3 = a2 filter (_ % 2 != 0)
a3.reverse

val seq: Seq[Int] = a1
val a4: Array[Int] = seq.toArray
a1 eq a4

def mesure( proc: => Unit ) {
  val start = System.currentTimeMillis
  proc
  println((System.currentTimeMillis - start) + "msec")
}

def evenElems[T : ClassManifest](xs : Vector[T]): Array[T] = {
  val arr = new Array[T]((xs.length + 1) / 2)
  for(i <- 0 until xs.length by 2)
    arr(i / 2) = xs(i)
  arr
}

evenElems(Vector(1,2,3,4,5))
evenElems(Vector("this", "is", "a", "test", "run"))

