def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toList

def grep(pattern: String) =
  for{
    file <- (new java.io.File("../2")).listFiles
    if file.getName.endsWith(".scala")
    (line,i) <- fileLines(file).zipWithIndex
    trimmed = line.trim
    if trimmed.matches(pattern)
  } println(file + " " + i.toString + ": " + trimmed)

grep("def.*")
