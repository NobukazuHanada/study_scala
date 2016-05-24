abstract class CCTherm {
  val description: String
  val yearMade: Int
  val dateObtained: String
  val bookPrice: Int
  val purchasePrice: Int
  val condition: Int
  override def toString = description

  def toXML =
    <cctherm>
  <description>{description}</description>
  <yearMade>{yearMade}</yearMade>
  <dateObtained>{dateObtained}</dateObtained>
  <bookPrice>{bookPrice}</bookPrice>
  <purchasePrice>{purchasePrice}</purchasePrice>
  <condition>{condition}</condition>
  </cctherm>

}

object CCTherm {
  def fromXML(node: scala.xml.Node): CCTherm =
    new CCTherm {
      val description = (node \ "description").text
      val yearMade = (node \ "yearMade").text.toInt
      val dateObtained = (node \ "dateObtained").text
      val bookPrice = (node \ "bookPrice").text.toInt
      val purchasePrice = (node \ "purchasePrice").text.toInt
      val condition = (node \ "condition").text.toInt
    }

}

object sample{
  def proc(node: scala.xml.Node): String =
    node match {
      case <a>{contents @ _*}</a> => "It's an a: " + contents
      case <b>{contents @ _*}</b> => "It's a b: "  + contents
      case _ => "It's something else."
    }

}

object Main extends App{
  val catalog =
    <catalog>
  <cctherm>
  <description>hot dog #5</description>
  <yearMade>1952</yearMade>
  <dateObtained>Match 14, 2006</dateObtained>
  <bookPrice>2199</bookPrice>
  <purchasePrice>500</purchasePrice>
  <condition>9</condition>
  </cctherm>
  <cctherm>
  <description>Sprite Boy</description>
  <yearMade>1964</yearMade>
  <dateObtained>April 28, 2003</dateObtained>
  <bookPrice>1695</bookPrice>
  <purchasePrice>595</purchasePrice>
  <condition>5</condition>
  </cctherm>
  </catalog>

  catalog match {
    case <catalog>{therms @ _*}</catalog> =>
      for (therm <- therms)
        println("proccessing: " +
          (therm \ "description").text)
  }

  catalog match {
    case <catalog>{therms @ _*}</catalog> =>
      for(therm @ <cctherm>{_*}</cctherm> <- therms)
        println("proccessing: "  +
          (therm \ "description").text)
  }

}

