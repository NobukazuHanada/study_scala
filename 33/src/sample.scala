import scala.util.parsing.combinator._

class Arith extends JavaTokenParsers {
  def expr: Parser[Any] = term~rep("+" ~ term | "-" ~ term)
  def term: Parser[Any] = factor~rep("*"~factor | "/"~factor)
  def factor: Parser[Any] = floatingPointNumber | "("~expr~")"
}

object MyParsers extends RegexParsers {
  val ident: Parser[String] = """[a-zA-Z_]\w*.""".r
}

class JSON extends JavaTokenParsers {
  def value: Parser[Any] = (
  obj
  | arr
  | stringLiteral
  | floatingPointNumber ^^ (_.toDouble)
  | "null" ^^ (x => null)
  | "true" ^^ (x => true)
    | "false" ^^ (x => false)
  ).withFailureMessage("illegal start of value")
  

  def obj: Parser[Map[String,Any]] =
    "{"~> repsep(member, ",") <~"}" ^^
  (Map() ++ _)

  def arr: Parser[List[Any]] =
    "["~> repsep(value, ",") <~"]"

  def member: Parser[(String, Any)] =
    stringLiteral~":"~value ^^
  { case name~":"~value => (name, value) }
}

import java.io.FileReader

object ParserJSON extends JSON with App{
  val reader = new FileReader(args(0))
  println(parseAll(value, reader))
}

object ParserExpr extends Arith with App {
  println("input: "+args(0))
  println(parseAll(expr, args(0)))
}
