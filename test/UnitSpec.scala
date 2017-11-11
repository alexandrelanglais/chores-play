import akka.actor.ActorSystem
import controllers.ChoresController
import org.scalatestplus.play._
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
 * Unit tests can run without a full Play application.
 */
class UnitSpec extends PlaySpec {

  "ChoresController" should {

    "return a valid result on action.ping" in {
      // actor system will create threads that must be cleaned up even if test fails
      val actorSystem = ActorSystem("test")
      try {
        implicit val ec = actorSystem.dispatcher
        val controller = new ChoresController(stubControllerComponents(), actorSystem)
        val resultFuture = controller.ping(FakeRequest())
        contentAsString(resultFuture) must be("pong")
      } finally {
        // always shut down actor system at the end of the test.
        actorSystem.terminate()
      }
    }

  }

}
