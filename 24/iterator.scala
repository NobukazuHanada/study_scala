def skipEmptyWords(it: BufferedIterator[String]) =
  while(it.head.isEmpty) { it.next() }


