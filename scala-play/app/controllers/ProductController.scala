package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer
import play.api.libs.json.{JsPath, Reads}

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
  implicit val productReads: Reads[Product] = (
    (JsPath \ "id").read[Long]and
      (JsPath \ "name").read[String]and
      (JsPath \ "price").read[Int]and
      (JsPath \ "category").read[String]and
      (JsPath \ "quantity").read[Int]
    )(Product.apply _)
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

case class CartMember (var id: Long, var name: String, var price: Int, var category: String, var quantity: Int)
object CartMember {
  var listBuffer: ListBuffer[CartMember] = {
    ListBuffer()
  }
  def save(cartMember: CartMember) = {
    listBuffer += cartMember
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

  // CART MEMBERS
  implicit val cartMemberWrites: Writes[CartMember] =
    (JsPath \ "id").write[Long]
      .and((JsPath \ "name").write[String])
      .and((JsPath \ "price").write[Int])
      .and((JsPath \ "category").write[String])
      .and((JsPath \ "quantity").write[Int])(unlift(CartMember.unapply))

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
      Accepted(Json.toJson(productToDelete))
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
      if (!Category.listBuffer.exists(item => item.name == newProduct.category)) {
        Category.listBuffer += new Category(Category.listBuffer.last.id + 1, newProduct.category)
      }
      Created(Json.toJson(Product.listBuffer.find(prod => {
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

    if (!Category.listBuffer.exists(item => item.name == category)) {
      NotFound("Category " + category + " not found!")
    } else {
      if (products.isEmpty) {
        NotFound("There's no products in category: " + category)
      } else {
        Ok(Json.toJson(products))
      }
    }
  }

  def updateCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    val categoryToUpdate = Category.listBuffer.find(cat => {
      cat.id == id
    }).orNull

    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    try {
      jsonBody
        .foreach {
          json => {
            categoryToUpdate.name = (json \ "name").as[String]
          }
        }

      Ok(Json.toJson(categoryToUpdate))
    } catch {
      case e: Exception => NotFound("Category with id: " + id + " was not found!")
    }
  }

  def deleteCategory(id: Long) = Action { implicit request: Request[AnyContent] =>
    val categoryToDelete = Category.listBuffer.find(cat => {
      cat.id == id
    }).orNull
    try {
      Category.listBuffer -= categoryToDelete
      Accepted(Json.toJson(categoryToDelete))
    } catch {
      case e: Exception => NotFound("Category with id: " + id + " was not found!")
    }
  }

  def addCategory() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    var incomingId: Long = -1
    var categoryOrNull: Category = null
    var newCategory: Category = null

    jsonBody
      .foreach { json => {
        incomingId = (json \ "id").as[Long]
        categoryOrNull = Category.listBuffer.find(cat => cat.id == incomingId).orNull
        newCategory = new Category(
          (json \ "id").as[Long],
          (json \ "name").as[String]
        )
      }
      }
    if (categoryOrNull != null) {
      NotFound("Category with id: " + incomingId + " already exists!")
    } else {
      Category.listBuffer += newCategory
      Created(Json.toJson(Category.listBuffer.find(cat => {
        cat.id == incomingId
      })))
    }
  }


  //  SHOPPING CART
  def showAllCartMembers() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.toJson(CartMember.listBuffer))
  }

  def showCartMember(id: Long) = Action { implicit request: Request[AnyContent] =>
    val cartMember = CartMember.listBuffer.find(cartMem => {
      cartMem.id == id
    }).orNull

    try {
      Ok(Json.toJson(cartMember))
    } catch {
      case e: Exception => NotFound("Product of id: " + id + " was not found in your cart!")
    }
  }

  def updateCartMember(id: Long) = Action { implicit request: Request[AnyContent] =>
//    try {
//      jsonBody
//        .foreach {
//          json => {
//            productToUpdate.name = (json \ "name").as[String]
//            productToUpdate.price = (json \ "price").as[Int]
//            productToUpdate.category = (json \ "category").as[String]
//            productToUpdate.quantity = (json \ "quantity").as[Int]
//          }
//        }
//
//      Ok(Json.toJson(productToUpdate))
//    } catch {
//      case e: Exception => NotFound("Product with id: " + id + " was not found!")
//    }

    try {

      val cartMemberToUpdate = CartMember.listBuffer.find(cartMem => {
        cartMem.id == id
      }).get

      val body: AnyContent = request.body
      val jsonBody: Option[JsValue] = body.asJson
      var quantityToSet = 0

      jsonBody
        .foreach {
          json => {
            quantityToSet = (json \ "quantity").as[Int]
        }
      }

      try {
        val actualProductCount = Product.listBuffer.find(prod => prod.id == cartMemberToUpdate.id).get.quantity

        // If there's enough of this product in stock
        if (actualProductCount >= quantityToSet) {
          cartMemberToUpdate.quantity = quantityToSet
          Ok(Json.toJson(cartMemberToUpdate))
        } else {
          NotAcceptable("Not enough of the '" + cartMemberToUpdate.name + "'(id: " + cartMemberToUpdate.id + ") product in stock! [Requested: " + (quantityToSet) + " / Available: " + actualProductCount + "]")
        }

      } catch {
        case e: Exception => NotFound("Product with id: " + id + " not found in stock!")
      }

    } catch {
      case e: Exception => NotFound("Product with id: " + id + " not found in your cart!")
    }
  }

  def deleteCartMember(id: Long) = Action { implicit request: Request[AnyContent] =>
    val cartMemberToDelete = CartMember.listBuffer.find(cartMem => {
      cartMem.id == id
    }).orNull
    try {
      CartMember.listBuffer -= cartMemberToDelete
      Accepted(Json.toJson(cartMemberToDelete))
    } catch {
      case e: Exception => NotFound("Product with id: " + id + " was not found in your cart!")
    }
  }

  def addCartMember() = Action { implicit request: Request[AnyContent] =>
    val body: AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson
    var newCartMember: CartMember = null

    jsonBody
      .foreach { json => {
        newCartMember = new CartMember(
          (json \ "id").as[Long],
          (json \ "name").as[String],
          (json \ "price").as[Int],
          (json \ "category").as[String],
          (json \ "quantity").as[Int]
        )
      }
    }

    // If there is such product in stock
    if (Product.listBuffer.exists(prod => prod.id == newCartMember.id)) {

      val actualProductCount = Product.listBuffer.find(prod => prod.id == newCartMember.id).get.quantity
      // If this product is already in the cart
      if (CartMember.listBuffer.exists(cartMem => cartMem.id == newCartMember.id)) {
        NotAcceptable("The product with id: " + newCartMember.id + " is already in your Cart!\n Consider using the PUT /cart/" + newCartMember.id + " endpoint to change it's quantity\nOR\n the DELETE /cart/" + newCartMember.id + " endpoint to remove it from your cart.")
      } else { // If this product isn't already in the cart
        // and There's enough of it in stock
        if (actualProductCount >= newCartMember.quantity) {
          CartMember.listBuffer += newCartMember
          Created(Json.toJson(newCartMember))
        } else {
          NotAcceptable("Not enough of the '" + newCartMember.name + "'(id: " + newCartMember.id + ") product in stock! [Requested: " + newCartMember.quantity + " / Available: " + actualProductCount + "]")
        }
      }

    } else {
      NotFound("Product of id: " + newCartMember.id + " not found in stock!")
    }
  }

  def buyCart() = Action { implicit request: Request[AnyContent] =>
    if (CartMember.listBuffer.isEmpty) {
      NotAcceptable("Your cart is empty! Add some products in order to buy them.")
    } else {


      // Check if the purchase can be made (if the quantities in stock are higher then in cart)
      var isQuantityOK = true
      for (member <- CartMember.listBuffer) {
        val productStock = Product.listBuffer.find(prod => prod.id == member.id).get
        if (productStock.quantity < member.quantity) {
          isQuantityOK = false
        }
      }
      if (!isQuantityOK) {
        NotAcceptable("The order's quantities exceed the available stock!")
      } else {

        // BUY
        val temporaryBuffer: ListBuffer[CartMember] = ListBuffer()
        for (member <- CartMember.listBuffer) {
          temporaryBuffer += member
          val productStock = Product.listBuffer.find(prod => prod.id == member.id).get
          productStock.quantity -= member.quantity
        }
        CartMember.listBuffer.clear()
        Ok(Json.toJson(temporaryBuffer))

      };


    }
  }
}
