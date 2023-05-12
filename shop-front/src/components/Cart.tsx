import { useContext } from "react";
import { useCart } from "react-use-cart";

import ShopContext from "../contexts/ShopContext";
import goods, { buyGoods, fetchGoods } from "../api/goods";

const priceFormat = (num: number): string => {
  return (Math.round(num * 100) / 100).toFixed(2);
};

function Cart() {
  let { setGoods } = useContext(ShopContext);
  const {
    isEmpty,
    totalUniqueItems,
    items,
    updateItemQuantity,
    removeItem,
    totalItems,
    cartTotal,
    emptyCart,
  } = useCart();

  const buyHelper = async () => {
    const money = Number(window.prompt("Insert Cash: 💵", "0.00"));

    if (cartTotal > money) {
      alert("Not enough money!");
      return;
    }
    await buyGoods(items, money);
    emptyCart();
    fetchGoods()
      .then((goodies) => {
        setGoods(goodies);
      })
      .catch((err) => {
        console.error(err);
        alert(err);
      });
  };

  if (isEmpty)
    return (
      <p className="m-20 text-2xl align-middle">
        Your cart is <b>empty</b> 🚮 <br />
        Go to <b>Browse</b> 🔝
      </p>
    );

  return (
    <div className="align-middle m-20">
      <h1 className="p-4 text-2xl">
        <b className="rounded-md border-2 border-gray-400 shadow-md p-2">
          Your shopping cart: 🛒
        </b>
      </h1>
      <div className="pb-4 pt-2 pl-5 ">
        <h2>Total item count: {totalItems}</h2>
        <h2>Total distinct item count: {totalUniqueItems}</h2>
        <h2>Total price: ${priceFormat(cartTotal)}</h2>
        <button
          className="text-2xl mt-4 p-2 active:bg-gray-300 hover:bg-gray-100 border border-gray-400 rounded-md shadow-md "
          onClick={buyHelper}
        >
          Buy 💵
        </button>
      </div>

      <ul className="pl-4">
        {items.map((item) => (
          <li key={item.id} className="shadow-sm flex justify-between pt-2">
            <span className="">
              <span className=" pr-2">
                <button
                  className="text-4xl m-0.5 mr-1 pb-1.5 pr-2 pl-2 rounded-md border shadow-md border-gray-400 bg-gray-500 opacity-80 text-gray-100 hover:bg-gray-100 hover:text-gray-500 active:bg-gray-300 "
                  onClick={() =>
                    item.quantity! < item.stock
                      ? updateItemQuantity(item.id, item.quantity! + 1)
                      : alert("None left!")
                  }
                >
                  +
                </button>
                <button
                  className="m-0.5 pr-3 pb-1.5 pl-3 text-4xl rounded-md shadow-md border border-gray-300 bg-gray-100 text-gray-500 opacity-80 hover:bg-gray-500 hover:text-gray-100 active:bg-gray-300 "
                  onClick={() =>
                    updateItemQuantity(item.id, item.quantity! - 1)
                  }
                >
                  -
                </button>
              </span>
              <span className="text-xl pl-0.5 pt-0.5">
                {item.name} x{item.quantity} ($
                {priceFormat(item.quantity! * item.price)})
              </span>
            </span>
            <button
              className="text-md m-0.5 pl-1 pr-1 rounded-md border shadow-md border-gray-400 hover:bg-gray-100 active:bg-gray-300 "
              onClick={() => {
                item.quantity = 0;
                removeItem(item.id);
              }}
            >
              🗑
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Cart;
