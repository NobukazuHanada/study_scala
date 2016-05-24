import ChecksumAccumlator.calculate

object FallWinterSprintSummer extends App {
  for(season <- List("fall", "winter", "sprint"))
    println(season + ": " + calculate(season))
}
