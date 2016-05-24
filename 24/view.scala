def measure( proc: => Unit ) {
  val start = System.currentTimeMillis
  proc
  println((System.currentTimeMillis - start) + "msec")
}

def lazyMap[T, U](coll: Iterable[T], f: T => U) =
  new Iterable[U] {
    def iterator = coll.iterator map f
  }

val v = Vector(1 to 1000: _*)
measure {
  v filter (_ % 2 == 0) map (_ / 2) filter (_ % 2 == 0) map (_ / 2)
}

measure {
  v.view filter (_ % 2 == 0) map (_ / 2) filter (_ % 2 == 0) map (_ / 2)
}

def negate(xs: collection.mutable.Seq[Int]) =
  for(i <- 0 until xs.length) xs(i) = -xs(i)

println()
println("not view")
val arr0 = (0 to 9).toArray
val subarr0 = arr0.slice(3,6)
negate(subarr0)
arr0

println()
println("view")
val arr = (0 to 9).toArray
val subarr = arr.view.slice(3,6)
negate(subarr)
arr
