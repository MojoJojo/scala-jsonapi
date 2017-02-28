package org.zalando.jsonapi.circe

import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.zalando.jsonapi.model.RootObject
import spray.http.ContentTypes
import spray.http.MediaTypes._
import spray.httpx.marshalling.Marshaller
import spray.httpx.unmarshalling.Unmarshaller

trait CirceJsonapiSupport extends CirceJsonapiEncoders with CirceJsonapiDecoders {
  implicit val circeJsonapiMarshaller: Marshaller[RootObject] = Marshaller.delegate[RootObject, String](
      `application/vnd.api+json`,
      `application/json`,
      ContentTypes.`application/json`
  )(_.asJson.noSpaces)
  implicit val circeJsonapiUnmarshaller: Unmarshaller[RootObject] = Unmarshaller.delegate[String, RootObject](
      `application/vnd.api+json`,
      `application/json`
  )(decode[RootObject](_).right.get)
}

object CirceJsonapiSupport extends CirceJsonapiSupport
