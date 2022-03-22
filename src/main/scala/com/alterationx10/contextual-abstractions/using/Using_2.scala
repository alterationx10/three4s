//> using scala "2.13.8"

trait BaseLogger {
    def log[T](t: T): Unit
}

case class PrintLogger() extends BaseLogger {
  def log[T](t: T): Unit = println(s"Logger result: ${t.toString}")
}

case class FancyLogger() extends BaseLogger {
  def log[T](t: T): Unit = println(s"Ye Olde Logger result: ${t.toString}")
}


object Using_2 extends App {

  def loggingOp[A,B](a: A, b: B)(implicit logger: BaseLogger): Int = {
      val result = a.toString.map(_.toInt).sum + b.toString.map(_.toInt).sum
      logger.log(result)
      result
  }

  val printLogger: PrintLogger = PrintLogger()
  val fancyLogger: FancyLogger = FancyLogger()

  loggingOp(40, 2)(printLogger)
  loggingOp(40, 2)(fancyLogger)


  implicit val defaultLogger = printLogger
  
  loggingOp(true, false)
  loggingOp(17, "purple")
  loggingOp("car", printLogger)(fancyLogger)

}
