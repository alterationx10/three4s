//> using scala "2.13.8"

import java.time.Instant
import java.time.temporal.ChronoUnit._

case class UpstreamUser(id: Long, created: Instant, lastSeen: Instant)

object UpstreamUserExtensions {
  implicit class ExtendedUpstreamUser(u: UpstreamUser) {
    def isStale: Boolean = {
      u.lastSeen.plus(7, DAYS).isBefore(Instant.now) &&
      u.created.plus(365, DAYS).isBefore(Instant.now)
    }
  }
}

object Extensions_2 extends App {

  val users: List[UpstreamUser] = List(
    UpstreamUser(
      1L,
      Instant.now.minus(30, DAYS),
      Instant.now.minus(10, MINUTES)
    ),
    UpstreamUser(
      2L,
      Instant.now.minus(365 * 2, DAYS),
      Instant.now.minus(6, DAYS)
    ),
    UpstreamUser(
      3L,
      Instant.now.minus(365, DAYS),
      Instant.now.minus(14, DAYS)
    )
  )

  import UpstreamUserExtensions._
  println("The following users are stale:")
  users.filter(_.isStale).foreach(println)
}
