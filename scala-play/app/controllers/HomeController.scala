package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
implicit val productsReads = Json.reads[Product]

class Product (var id: Long, var price: Int) {
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val productsContainer: List[Product] = List()

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def explore() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.explore())
  }

  def tutorial() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.tutorial())
  }

  def hello(name: String) = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.hello(name))
  }

  def product(id: Long) = Action { implicit request: Request[AnyContent] =>
    if (true) NotFound(<h1>LOL {id} </h1>)
    else Ok("LoL: " + id)
  }

  def products() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.parse())
  }
}
