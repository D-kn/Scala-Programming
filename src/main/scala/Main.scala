import scala.util.{Random, Try}

object Main {
  def main(args: Array[String]): Unit = {
    val path = "C:\\Users\\User\\Documents\\Cours Data Engineering\\Java & Scala\\TP\\moviesSemiCol.csv"
    val buf = io.Source.fromFile(path)

    var cinema = List[Movie]()
    var directors = List[Director]()
    var actors = List[Actor]()

    var directorsList = Set[String]()
    var actorsList = Set[String]()

    for(line <- buf.getLines().drop(1)){
      val cols = line.split(";").map(_.trim)

      if (cols.length == 11) {
        val title = cols(0)
        val year = Try(cols(1).toInt).getOrElse(0)
        val director = cols(5).split(",").map(_.trim).toList
        val actor = cols(6).split(",").map(_.trim).toList
        val rating = Try(cols(8).toDouble).getOrElse(0.0)

        val cleanedDirectors = director.map(_.replace("[", "").replace("]", "").replace("\"", ""))
        val cleanedActors = actor.map(_.replace("[", "").replace("]", "").replace("\"", ""))


        val movie = new Movie(title, year, cleanedDirectors, cleanedActors, rating)

        directorsList ++= cleanedDirectors
        actorsList ++= cleanedActors

        cinema = cinema :+ movie

      }
    }

//    Conparer deux films entre eux
    val random = new Random()
    val index1 = random.nextInt(cinema.length)
    var index2 = random.nextInt(cinema.length)

    while (index2 == index1) {
      index2 = random.nextInt(cinema.length)
    }

    val movie01 = cinema(index1)
    val movie02 = cinema(index2)
    println(s"'${movie02.title}' est-il plus coté que '${movie01.title}' ? ${movie01 < movie02}")

//    cinema.foreach(movie => println(movie.title, movie.year, movie.rating, movie.director, movie.actor))
//    println(actorsList)

    directorsList.foreach { fullName =>
      val fNameSplitted = fullName.split(" ")
      val (firstName, lastName) = if (fNameSplitted.length >= 2) (fNameSplitted(0), fNameSplitted(1)) else ("", "")
      val direcotorsMovies = cinema.filter(movie => movie.director.contains(fullName))
      val director_ = new Director(firstName, lastName, direcotorsMovies)
      directors = directors :+ director_
    }

    actorsList.foreach { fullName =>
      val fNameSplitted = fullName.split(" ")
      val (firstName, lastName) = if (fNameSplitted.length >= 2) (fNameSplitted(0), fNameSplitted(1)) else ("", "")
      val actorMovies = cinema.filter(movie => movie.actor.contains(fullName))
      val actor_ = new Actor(firstName, lastName, actorMovies)
      actors = actors :+ actor_
    }

//    top 20 realisateur
    val top20Directors = directors.headOption.map(_.top20Directors(directors)).getOrElse(Nil)
//    println("top 20 realisateurs :")
//    top20Directors.foreach { director =>
//      println(s"${director.firstName} ${director.lastName} - moyenne : ${director.averageRating}")
//    }


//    cinematheque2 --> c'est-a-dire a partir de 2015
    val recentCinema = cinema.filter(movie => movie.year >= 2015)
//    recentCinema.foreach(movie => println(movie.year))

//    cinematheque avec une note a partir de 8
    val highRatedCinema = cinema.filter(movie => movie.rating >= 8.0*100.00)
//    highRatedCinema.foreach(movie => println(movie.year, movie.rating))

//    troisieme cinematheque avec les criteres de mon choix
    val recenthighRatedCinema = cinema.filter(movie => movie.year >= 2016 && movie.rating >= 7.0)
//    recenthighRatedCinema.foreach(movie => println(movie.year, movie.rating))

    val user1 = new User("Dicken", "NGOLO", highRatedCinema)
    val movie1 = user1.searchMovies(year = Some(2016))

//    movie1.foreach { movie =>
//      println(s"Titre : ${movie.title}")
//      println(s"Année : ${movie.year}")
//      println(s"Note : ${movie.rating}")
//      println(s"Réalisateur : ${movie.director}")
//      println(s"Acteur : ${movie.actor}")
//      println()
//    }

//    user1.borrowMovie(1, movie1) //le premier livre de ma liste par exemeple
//    user1.listBorrowedMovies()
    buf.close()
  }
}
