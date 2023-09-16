import scala.collection.mutable._

class Movie(mTitle : String, mYear: Int, mRating: Double, mDirector:String, mActor:String) extends Comparable {
  def title: String = mTitle
  def year: Int = mYear
  def rating: Double = mRating
  def Director: String = mDirector
  def Actor: String = mActor

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
