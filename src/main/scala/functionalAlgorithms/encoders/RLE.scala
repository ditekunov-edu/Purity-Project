package functionalAlgorithms.encoders

object RLE {

  /**
    * Packs repeated elements in a list of lists
    */
  def pack[A](input: List[A]): List[List[A]] = {
    if (input.isEmpty) { List(List()) }
    else {
      val (packed, next) = input span { _ == input.head }

      if (next == Nil) List(packed)
      else packed :: pack(next)
    }
  }

  /**
    * Returns RLE-encoded sequence of tuples in (7, 'a) style
    */
  def encoding[A](input: List[A]): List[(Int, A)] = {
    ( if (input.isEmpty) { List(List()) }
    else {
      val (packed, next) = input span { _ == input.head }

      if (next == Nil) List(packed)
      else packed :: pack(next)

    }) map { element => (element.length, element.head) }
  }
}