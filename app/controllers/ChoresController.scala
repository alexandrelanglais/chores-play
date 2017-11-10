package controllers

import javax.inject._

import akka.actor.ActorSystem
import model.Chore
import play.api.mvc._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.concurrent.Promise
import play.api.libs.json._

import scala.util.parsing.json.JSON

@Singleton
class ChoresController @Inject()(
    cc: ControllerComponents,
    actorSystem: ActorSystem)(implicit exec: ExecutionContext)
    extends AbstractController(cc) {

  implicit val jsonFormatList = Json.reads[Chore]
  implicit val jsonFormatChore = Json.writes[Chore]

  val testChores = List(Chore(1, "Vaisselle"), Chore(2, "Lessive"))
  def ping = Action { Ok("pong") }

  def list = Action { Ok(Json.toJson(testChores)) }
}
