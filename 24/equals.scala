import collection.mutable.{HashMap, ArrayBuffer}

val buf = ArrayBuffer(1,2,3)
val map = HashMap(buf -> 3)
map(buf)
buf(0) += 1
try {
  map(buf)
} catch {
  case e:Exception => println("error!")
}

buf(0) = 1

try {
  map(buf)
} catch {
  case e:Exception => println("error!")
}
