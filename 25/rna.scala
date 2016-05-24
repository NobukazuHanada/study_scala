abstract class Base
case object A extends Base
case object T extends Base
case object G extends Base
case object U extends Base
object Base {
  val fromInt: Int => Base = Array(A,T,G,U)
  val toInt: Base => Int = Map(A -> 0, T -> 1, G -> 2, U -> 3)
}

import collection.IndexedSeqLike
import collection.mutable.{Builder, ArrayBuffer}
import collection.generic.CanBuildFrom

 
final class RNA private (val groups: Array[Int],
  val length: Int) extends IndexedSeq[Base] with IndexedSeqLike[Base, RNA] {

  import RNA._

  override protected[this] def newBuilder: Builder[Base, RNA] =
    RNA.newBuilder

  def apply(idx: Int): Base = {
    if(idx < 0 || length <= idx)
      throw new IndexOutOfBoundsException
    Base.fromInt(groups(idx/N) >> (idx % N * S) & M)
  }

  override def foreach[U](f : Base => U): Unit = {
    var i = 0
    var b = 0
    while(i < length){
      b = if(i % N == 0) groups(i / N) else b >>> S
      f(Base.fromInt(b & M))
      i += 1
    }
  }
}

object RNA {
  private val S = 2
  private val N = 32 / S;
  private val M = (1 << S) - 1
  def fromSeq(buf: Seq[Base]): RNA = {
    val groups = new Array[Int]((buf.length + N - 1) / N)
    for(i <- 0 until buf.length)
      groups(i / N) |= Base.toInt(buf(i)) << (i % N * S)
    new RNA(groups, buf.length)
  }
  def apply(bases: Base*) = fromSeq(bases)

  def newBuilder: Builder[Base, RNA] =
    new ArrayBuffer mapResult fromSeq

  implicit def canBuildFrom: CanBuildFrom[RNA, Base, RNA] =
    new CanBuildFrom[RNA, Base, RNA] {
      def apply(): Builder[Base, RNA] = newBuilder
      def apply(from: RNA): Builder[Base, RNA] = newBuilder
    }

}

