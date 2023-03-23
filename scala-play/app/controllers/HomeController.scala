package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
implicit val productsReads = Json.reads[Product]

class Product (var id: Long, var price: Int, category: String, quantity: Int) {
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val products: List[Product] = List()
  private val categories: List[Product] = List()
  private val cart: List[Product] = List()

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
  // PRODUCTS
  def showAllProducts() = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def showProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    if (true) NotFound(<h1>LOL
      {id}
    </h1>)
    else Ok("LoL: " + id)
  }

  def updateProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def deleteProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def addProduct() = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }
  //  CATEGORIES
def showAllCategories() = Action { implicit request: Request[AnyContent] =>
  Ok("lol")
}

  def showCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    if (true) NotFound(<h1>LOL
      {id}
    </h1>)
    else Ok("LoL: " + id)
  }

  def updateCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def deleteCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def addCategory() = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }
  //  SHOPPING CART
  def showAllCartMembers() = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def showCartMember(id: Long) = Action { implicit request: Request[AnyContent] =>
    if (true) NotFound(<h1>LOL
      {id}
    </h1>)
    else Ok("LoL: " + id)
  }

  def updateCartMember(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def deleteCartMember(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }

  def addCartMember() = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }
}
