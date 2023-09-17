
class Director (_firstName: String, _lastName: String, _movies:List[Movie]) extends Person with Personality {
  override def firstName: String = _firstName
  override def lastName: String = _lastName
  override def movies: List[Movie] = _movies

  override def averageRating: Double = this.movies.map(_.rating).sum / movies.length


  override def equals(toCompare: Any): Boolean = {
    toCompare.isInstanceOf[Director] && {
      val m = toCompare.asInstanceOf[Director]
      m.averageRating == this.averageRating
    }
  }

  override def <(toCompare: Any): Boolean = {
    toCompare.isInstanceOf[Director] && {
      val m = toCompare.asInstanceOf[Director]
      this.averageRating < m.averageRating
    }
  }

  def top20Directors(allDirectors: List[Director]): List[Director] = {
      val sortedDirectors = allDirectors.sortBy(_.averageRating)(Ordering[Double].reverse)
      sortedDirectors.take(20)
  }
}
