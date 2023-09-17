class User (_firstName: String, _lastName: String, _cinema: List[Movie])  {
  def firstName: String = _firstName
  def lastName: String = _lastName
  def cinema: List[Movie] = _cinema

  var movieBorrowed: List[Movie] = List.empty

  def searchMovies(title: Option[String] = None, year: Option[Int] = None, director: Option[String] = None, actor: Option[String] = None): List[Movie] = {
    cinema.filter { movie =>
      (title.isEmpty || title.exists(movie.title.contains)) &&
        (year.isEmpty || year.contains(movie.year)) &&
        (director.isEmpty || director.exists(movie.director.contains)) &&
        (actor.isEmpty || actor.exists(movie.actor.contains))
    }
  }

  def borrowMovie(index: Int, movies: List[Movie]): Unit = {
    if (index >= 1 && index < movies.length+1) {
      val movieToBorrow = movies(index-1)
      if (!movieBorrowed.contains(movieToBorrow)) {
        movieBorrowed = movieToBorrow :: movieBorrowed
        println(s"Le film '${movieToBorrow.title}' a été emprunté avec succès")
      } else {
        println(s"Le film '${movieToBorrow.title}' est déjà emprunté")
      }
    } else {
      println("Index invalid --> aucune action effectuée.")
    }
  }

  def listBorrowedMovies(): Unit = {
    if (movieBorrowed.isEmpty){
      println(s"Aucun film emprunté par ${this.firstName} ${this.lastName} ")
    } else {
      print(s"Films empruntés par ${this.firstName} ${this.lastName} :")
      movieBorrowed.foreach(movie => println(s"${movie.title} (${movie.year})"))
    }
  }
}
