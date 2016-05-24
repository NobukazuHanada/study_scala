object Email extends ((String, String) => String) {
  def apply(user: String, domain: String) =
    user + "@" + domain

  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"
    if (parts.length == 2) Some(parts(0), parts(1)) else None
  }
}

object Twice {
  def apply(s: String): String = s + s

  def unapply(s: String): Option[String] = {
    val length = s.length / 2
    val half = s.substring(0, length)
    if ( half == s.substring(length)) Some(half) else None
  }
}

object UpperCase {
  def unapply(s: String): Boolean = s.toUpperCase == s
}

object Sample{
  def userTwiceUpper(s: String) = s match {
    case Email(Twice(x @ UpperCase()), domain) =>
      "match: " + x + " in domain"  + domain
    case _ =>
      "no match"
  }
}

object Domain {
  def apply(parts: String*): String =
    parts.reverse.mkString(".")

  def unapplySeq(whole: String): Option[Seq[String]] =
    Some(whole.split("\\.").reverse)

  def isTomInDotCom(s: String): Boolean = s match {
    case Email("tom", Domain("com", _*)) => true
    case _ => false
  }
}

object ExpandedEmail {
  def unapplySeq(email: String)
      : Option[(String, Seq[String])] = {
    val parts = email split "@"
    if(parts.length == 2)
      Some(parts(0), parts(1).split("\\.").reverse)
    else
      None
  }
}

