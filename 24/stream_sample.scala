val str = 1 #:: 2 #:: 3 #:: Stream.empty

def fibFrom(a: Int, b:Int) : Stream[Int] =
  a #:: fibFrom(b, a+b)

