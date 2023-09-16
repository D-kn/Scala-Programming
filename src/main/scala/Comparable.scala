trait Comparable {
  def < (toCompare: Any): Boolean
  def equals(toCompare: Any) : Boolean

  def <= (toCompare: Any): Boolean = (this < toCompare) || (this == toCompare)
  def > (toCompare: Any): Boolean = !(this <= toCompare)
  def >= (toCompare: Any): Boolean = !(this < toCompare)

}
