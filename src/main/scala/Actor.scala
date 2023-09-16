

class Actor(_firstName: String, _lastName: String, _movies: List[Movie]) extends Person with Personality{
  override def firstName: String = _firstName
  override def lastName: String = _lastName
  override def movies:  List[Movie] = _movies

  override def averageRating: Double = this.movies.map(_.rating).sum / movies.length

}
