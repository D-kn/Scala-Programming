trait Personality {
  def movies: List[Movie]
  def averageRating: Double = this.movies.map(_.rating).sum / movies.length

}
