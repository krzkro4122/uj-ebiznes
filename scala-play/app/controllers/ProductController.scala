package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.http._
import play.api.libs.json._
import play.api.libs.functional.syntax._

import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionException

case class Product (var id: Long, var name: String, var price: Int, var category: String, var quantity: Int)
object Product {
  var listBuffer: ListBuffer[Product] = {
    ListBuffer(
      Product(0, "Coconut Oil", 69, "food", 420),
      Product(1, "Elmo's Mask", 99, "costumes", 10),
      Product(2, "Beer", 5, "food", 200),
      Product(3, "Peanut Butter", 6, "food", 100),
      Product(4, "Jacket for a small dog", 69, "costumes", 100),
      Product(5, "Taiwan", 1000000, "countries", 1),
      Product(6, "San Marino", 1, "countries", 1),
    )
  }
  def save(product: Product) = {
    listBuffer += product
  }
}

case class Category (var id: Long, var name: String)
object Category {
  var listBuffer: ListBuffer[Category] = {
    ListBuffer(
      Category(0, "food"),
      Category(1, "costumes"),
      Category(2, "countries")
    )
  }
  def save(category: Category) = {
    listBuffer += category
  }
}

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  private val cart: List[Product] = List()

  // PRODUCTS
  implicit val productWrites: Writes[Product] =
    (JsPath \ "id").write[Long]
      .and((JsPath \ "name").write[String])
      .and((JsPath \ "price").write[Int])
      .and((JsPath \ "category").write[String])
      .and((JsPath \ "quantity").write[Int])(unlift(Product.unapply))

  // CATEGORIES
  implicit val categoryWrites: Writes[Category] =
    (JsPath \ "id").write[Long]
      .and((JsPath \ "name").write[String])(unlift(Category.unapply))

  def showAllProducts() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(Product.listBuffer))
  }

  def showProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    val product = Product.listBuffer.find(prod => {prod.id == id}).orNull

    try {
      Ok(Json.toJson(product))
    } catch {
      case e: Exception => NotFound("Product of id: " + id + " was not found!")
    }
  }

  def updateProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    val productToUpdate = Product.listBuffer.find(prod => {
      prod.id == id
    }).orNull

    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    try {
      jsonBody
        .foreach {
          json => {
            productToUpdate.name = (json \ "name").as[String]
            productToUpdate.price = (json \ "price").as[Int]
            productToUpdate.category = (json \ "category").as[String]
            productToUpdate.quantity = (json \ "quantity").as[Int]
          }
        }

      Ok(Json.toJson(productToUpdate))
    } catch {
      case e: Exception => NotFound("Product with id: " + id + " was not found!")
    }
  }

  def deleteProduct(id: Long) = Action { implicit request: Request[AnyContent] =>
    val productToDelete = Product.listBuffer.find(prod => {
      prod.id == id
    }).orNull
    try {
      Product.listBuffer -= productToDelete
      Ok(Json.toJson(productToDelete))
    } catch {
      case e: Exception => NotFound("Product with id: " + id + " was not found!")
    }
  }

  def addProduct() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    var incomingId: Long = -1
    var productOrNull: Product = null
    var newProduct: Product = null

    jsonBody
      .foreach { json => {
        incomingId = (json \ "id").as[Long]
        productOrNull = Product.listBuffer.find(prod => prod.id == incomingId).orNull
        newProduct = new Product(
          (json \ "id").as[Long],
          (json \ "name").as[String],
          (json \ "price").as[Int],
          (json \ "category").as[String],
          (json \ "quantity").as[Int]
        )
      }}
    if (productOrNull != null) {
      NotFound("Product with id: " + incomingId + " already exists!")
    } else {
      Product.listBuffer += newProduct
      Ok(Json.toJson(Product.listBuffer.find(prod => {
        prod.id == incomingId
      })))
    }
  }


  //  CATEGORIES
  def showAllCategories() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(Category.listBuffer))
  }

  def showCategory(category: String) = Action { implicit request: Request[AnyContent] =>
    val products = Product.listBuffer.filter(prod => {
      prod.category == category
    })

    try {
      Ok(Json.toJson(products))
    } catch {
      case e: Exception => NotFound("Product of category: " + category + " were not found!")
    }
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
