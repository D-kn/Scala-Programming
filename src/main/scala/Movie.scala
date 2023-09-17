import scala.collection.mutable._

class Movie(mTitle : String, mYear: Int, mDirector:List[String], mActor:List[String], mRating: Double) extends Comparable {
  def title: String = mTitle
  def year: Int = mYear
  def rating: Double = mRating
  def director: List[String] = mDirector
  def actor: List[String] = mActor


//  def from2015Movies: List[Move]

  override def equals(toCompare: Any) : Boolean = {
    toCompare.isInstanceOf[Movie] && {
      val m = toCompare.asInstanceOf[Movie]
      m.rating == this.rating
    }}

  override def < (toCompare: Any): Boolean = {
    toCompare.isInstanceOf[Movie] && {
      val m = toCompare.asInstanceOf[Movie]
      this.rating < m.rating
    }
  }




}
