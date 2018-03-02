package com.test.refined

import eu.timepit.refined.W
import eu.timepit.refined.api.{Refined, Validate}
import eu.timepit.refined.string._
import akka.http.scaladsl.model.{Uri => AkkaUri}

import scala.util.Try

object Refinements extends CustomRefinements {
  type PrefixName = String Refined MatchesRegex[W.`"[a-zA-Z_][a-zA-Z0-9-_.]*"`.T]
  type Reference = String Refined IRelativeRef
}

trait CustomRefinements {
  final case class IRelativeRef()

  object IRelativeRef {

    private def akkaUri(s: String): Try[AkkaUri] =
      Try(AkkaUri(s)).filter(_.isAbsolute)

    final implicit def iRelativeRefValidate
      : Validate.Plain[String, IRelativeRef] = {
      def validIRelativeRef(s: String): Boolean =
        akkaUri(s"http://example.com/$s").map(_ => true).getOrElse(false)

      Validate.fromPredicate(s => validIRelativeRef(s),
                             s => s"ValidIRelativeRef($s)",
                             IRelativeRef())
    }
  }
}
