trait Abstract {
  type T
  def transform(x: T): T
  val initial: T
  var current: T
}

class Concrete extends Abstract {
  type T = String
  def transform(x: String) = x + x
  val initial = "hi"
  var current = initial
}

abstract class Fruit {
  val v: String
  def m: String
}

abstract class Apple extends Fruit {
  val v: String
  val m: String
}

trait AbstractTime {
  var hour: Int
  var minite: Int
}

trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
}

trait RationalTrait {
  val numerArg: Int
  val denomArg: Int
  require(denomArg != 0)
}

trait LazyRationalTrait {
  val numerArg: Int
  val denomArg: Int
  lazy val numer = numerArg / g
  lazy val denom = denomArg / g
  override def toString = numer + "/" + denom
  private lazy val g = {
    require(denomArg != 0)
    gcd(numerArg, denomArg)
  }
  private def gcd(a: Int, b: Int): Int =
    if( b == 0 ) a else gcd(b, a % b)
}
