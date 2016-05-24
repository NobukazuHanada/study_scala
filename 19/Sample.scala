trait RNG {
  def nextInt: (Int, RNG)
}

type Rand[+A] = RNG => (A, RNG)

case class SimpleRNG(seed: Long) extends RNG {

  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

}

def nonNegativeInt(rng: RNG): (Int, RNG) = {
  val (i, r) = rng.nextInt
  (if (i < 0) -(i + 1) else i, r)
}

def map[A, B](s: Rand[A])(f: A => B): Rand[B] =
  rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

val double: Rand[Double] =
  map (nonNegativeInt) (_.toDouble/(Int.MaxValue.toDouble))

val string: Rand[String] =
  map (
    map (nonNegativeInt) (_/(Int.MaxValue.toDouble))
  ) (_.toString)

def unit[A](a: A): Rand[A] = rng => (a, rng)

def map2[A,B,C] (ra: Rand[A], rb : Rand[B]) (f: (A,B) => C): Rand[C] =
  rng => {
    val (a, rng1) = ra(rng)
    val (b, rng2) = rb(rng1)
    (f(a,b),rng2)
  }


var logger : Array[String] = Arry()

val checkRNG[A](log:String)(a:A) : Rand[A] =
  rng => {
    logger :+= log
    (a, rng)
  }
