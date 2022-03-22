//> using scala "3.0.2"

trait BaseLogger {
  def log[T](t: T): Unit
}

case class PrintLogger() extends BaseLogger {
  def log[T](t: T): Unit = println(s"Logger result: ${t.toString}")
}

case class FancyLogger() extends BaseLogger {
  def log[T](t: T): Unit = println(s"Ye Olde Logger result: ${t.toString}")
}

object Using_3 {

  // You can specify the name logger, but don't have to
  def loggingOp_withParamName[A, B](a: A, b: B)(using logger: BaseLogger): Int = {
    val result = a.toString.map(_.toInt).sum + b.toString.map(_.toInt).sum
    logger.log(result)
    result
  }

  def loggingOp[A, B](a: A, b: B)(using BaseLogger): Int = {
    val logger = summon[BaseLogger]
    val result = a.toString.map(_.toInt).sum + b.toString.map(_.toInt).sum
    logger.log(result)
    result
  }

  val printLogger: PrintLogger = PrintLogger()
  val fancyLogger: FancyLogger = FancyLogger()

  @main
  def main = {

    loggingOp(40, 2)(using printLogger)
    loggingOp(40, 2)(using fancyLogger)

    // implicit val defaultLogger = printLogger // <- this still works!
    given defaultLogger: BaseLogger = printLogger

    loggingOp(true, false)
    loggingOp(true, false)
    loggingOp(17, "purple")
    loggingOp("car", printLogger)(using fancyLogger)
  }

}
