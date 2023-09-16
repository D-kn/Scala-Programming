import scala.util.Try
import scala.util.control.Breaks.break

object Main {
  def main(args: Array[String]): Unit = {
    val path = "C:\\Users\\User\\Documents\\Cours Data Engineering\\Java & Scala\\TP\\moviesSemiCol.csv"
    val buf = io.Source.fromFile(path)
    for(line <- buf.getLines().drop(1)){
      val cols = line.split(";").map(_.trim)
//      cols.foreach(col => println(col))
      if (cols.length == 11) {
        val title = cols(0)
        val year = Try(cols(1).toInt).getOrElse(0)
        val director = cols(5)
        val stars = cols(6)
        val rating = Try(cols(8).toDouble).getOrElse(0.0)

/**
        // Créez une instance de Movie avec les valeurs extraites
        var movies = new Movie(title, year, rating, director, stars)
        println(s"Title: $title")
        println(s"Year: $year")
        println(s"Actors: $stars")
        println(s"Director: $director")
        println(s"Rating: $rating")
        println("------------"*8)
//        println(movies)


      }
  }
    buf.close()

  }}
