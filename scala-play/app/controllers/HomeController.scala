package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.collection.mutable.ListBuffer

case class Product (var id: Long, name: String, var price: Int, category: String, quantity: Int)
object Product {
  var listBuffer: ListBuffer[Product] = {
    ListBuffer(
      Product(0, "Coconut Oil", 69, "Cooking", 420),
      Product(1, "Elmo's Mask", 99, "Costumes", 10),
      Product(2, "Beer", 5, "Alcohol", 200)
    )
  }
  def save(product: Product) = {
    listBuffer += product
  }
}
class Category (var id: Long, var name: String)

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val categories: List[Category] = List()
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
  implicit val productWrites: Writes[Product] =
    (JsPath \ "id").write[Long]
      .and((JsPath \ "name").write[String])
      .and((JsPath \ "price").write[Int])
      .and((JsPath \ "category").write[String])
      .and((JsPath \ "quantity").write[Int])(unlift(Product.unapply))

  def showAllProducts() = Action { implicit request: Request[AnyContent] =>
    val json = Json.toJson(Product.listBuffer)
    Ok(json)
  }

  def showProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(Product.listBuffer.find(prod => {prod.id == id})))
  }

  def updateProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

//    jsonBody
//      .map { json => {
//        val prod = Product.listBuffer.find(prod => {prod.id == id})
//        prod.copy()
//              (json \ "id").as[Long),
//              (json \ "name").as[String],
//              (json \ "price").as[Int],
//              (json \ "category").as[String],
//              (json \ "quantity").as[Int]
//        )
//      }

    Ok(Json.toJson(Product.listBuffer.find(prod => {prod.id == id})))
  }

  def deleteProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    val productToDelete = Product.listBuffer.find(prod => {prod.id == id}).get
    Product.listBuffer -= productToDelete
    Ok(Json.toJson(productToDelete))
  }

  def addProduct() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    var incomingId: Long = -1

    jsonBody
      .map { json => {
        incomingId = (json \ "id").as[Long]
        Product.listBuffer += new Product(
            (json \ "id").as[Long],
            (json \ "name").as[String],
            (json \ "price").as[Int],
            (json \ "category").as[String],
            (json \ "quantity").as[Int]
        )
      }}

    Ok(Json.toJson(Product.listBuffer.find(prod => {
      prod.id == incomingId
    })))
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

  def buyCart() = Action { implicit request: Request[AnyContent] =>
    Ok("lol")
  }
}
