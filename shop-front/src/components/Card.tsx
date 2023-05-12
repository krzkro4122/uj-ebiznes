import { useCart } from "react-use-cart";

import { IGood } from "../interface";
import { Item } from "react-use-cart";

export interface CardProps {
  good: IGood;
  quan?: number;
  key: string;
}

const Card = (props: CardProps) => {
  const { addItem, inCart, updateItemQuantity, getItem } = useCart();

  const itemCheck = () => {
    let id = String(props.good.ID);
    if (inCart(id)) {
      const item: Item = getItem(id);
      item.stock! > item.quantity!
        ? updateItemQuantity(id, item.quantity! + 1)
        : alert("None left!");
      console.log(getItem(id));
    } else {
      const item = {
        id: id,
        price: props.good.Price,
        quantity: 1,
        name: props.good.Name,
        group: props.good.Category,
        categoryID: props.good.CategoryID,
        stock: props.good.Stock,
        thumbnail: props.good.Thumbnail,
      };
      addItem(item, 1);
      console.log(item);
    }
  };

  const isDisabled = () => {
    let id = String(props.good.ID);

    if (inCart(id)) {
      const item: Item = getItem(id);
      return item.stock! <= item.quantity!;
    } else {
      return false;
    }
  };

  return (
    <div className="card shadow-md w-80 p-1 m-2 float-left border-solid border border-black rounded">
      <div className="flex justify-center">
        <img
          src={props.good.Thumbnail}
          alt={`Thumbnail of ${props.good.Name}`}
          className="shadow-md p-0.5 h-52"
        />
      </div>
      <div className="flex justify-between p-2 pt-4">
        <p>
          <b>{props.good.Name}</b>
        </p>
        <span className="text-gray-500">{props.good.Group}</span>
      </div>
      <div className="flex justify-between p-2">
        <button disabled={isDisabled()} onClick={itemCheck}>
          <span
            className={`buy border shadow-md rounded-md p-1.5 border-solid ${
              isDisabled()
                ? "text-gray-100 bg-gray-300 border-gray-300 cursor-not-allowed"
                : "text-white bg-gray-400 border-gray-400 active:bg-gray-200 hover:text-gray-500 hover:bg-white"
            }`}
          >
            Buy
          </span>{" "}
          <span className="text-gray-600">for ${props.good.Price}</span>
        </button>
        <span className="text-gray-500">{props.good.Stock} pcs.</span>
      </div>
    </div>
  );
};

export default Card;
