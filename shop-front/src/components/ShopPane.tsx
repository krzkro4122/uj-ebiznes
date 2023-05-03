import { useContext } from "react";
import ShopContext from "../contexts/ShopContext";
import { IGood } from "../interface";
import Card from "./Card";

export interface GoodsProps {}

export const ShopPane = (props: GoodsProps): JSX.Element => {
  const { goods } = useContext(ShopContext);
  // const { cart, addGood, removeGood } = useCart();

  return (
    <div className="p-2 flex flex-wrap" key={1}>
      {goods?.map((good: IGood) => (
        <Card
          good={good}
          quan={good.Quantity}
          key={good.Name + Date.now().toString()}
        />
      ))}
    </div>
  );
};

export default ShopPane;
