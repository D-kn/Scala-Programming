import scala.util.Try

object Main {
  def main(args: Array[String]): Unit = {
    val path = "C:\\Users\\User\\Documents\\Cours Data Engineering\\Java & Scala\\TP\\moviesSemiCol.csv"
    val buf = io.Source.fromFile(path)

    var cinema = List[Movie]()
  

    for(line <- buf.getLines().drop(1)){
      val cols = line.split(";").map(_.trim)
      if (cols.length == 11) {
        val title = cols(0)
        val year = Try(cols(1).toInt).getOrElse(0)
        val director = cols(5)
        val stars = cols(6)
        val rating = Try(cols(8).toDouble).getOrElse(0.0)

        val movie = new Movie(title, year, rating, director, stars)

        cinema = cinema :+ movie
      }
    }

    cinema.foreach(movie => println(movie.title, movie.year, movie.rating, movie.director, movie.actor))
    buf.close()
  }
}
