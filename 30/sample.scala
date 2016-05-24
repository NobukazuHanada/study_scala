class Point(val x: Int, val y: Int){
  override def hashCode = 41 * (41 + x) + y
  override def equals(other: Any): Boolean = other match {
    case that:Point =>
      (that canEqual this) &&
      (this.x == that.x) && (this.y == that.y)
    case _ => false
  }
  def canEqual(other: Any) = other.isInstanceOf[Point]
}

object Color extends Enumeration {
  val Red, Orange, Yellow, Green, Blue, Indigo, Violet = Value
}

class ColoredPoint(x: Int, y: Int, val color: Color.Value)
    extends Point(x,y){
  override def hashCode = 41 * super.hashCode + color.hashCode
  override def equals(other: Any) = other match {
    case that: ColoredPoint =>
      super.equals(that) && this.color == that.color
    case _ => false
  }
  override def canEqual(other: Any) =
    other.isInstanceOf[ColoredPoint]
}
