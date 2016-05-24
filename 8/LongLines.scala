import scala.io.Source
object LongLines {
  def processFile(filename: String, width: Int) {
    def processLine(line: String) {
      if(line.length > width )
        println(filename + ": " + line.trim)
    }

    val source = Source.fromFile(filename)
    for( line <- source.getLines() )
      processLine(line)
  }

}

object FindLongLines {
  def main(args: Array[String]) {
    val width = args(0).toInt
    for(arg <- args.drop(1))
      LongLines.processFile(arg, width)
  }
}


class Thermometer{
  var celsius: Float = _
  def fahrenheit = celsius * 9 / 5 + 32
  def fahrenheit_= (f: Float) {
    celsius = (f - 32) * 5 / 9
  }
  override def toString = fahrenheit + "F/" + celsius + "C"  
}
