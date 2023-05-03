import React, { useEffect, useState } from "react";
import { IGood, IShopContextState } from "../interface";
import { fetchGoods } from "../api/goods"

const defaultValue: IShopContextState = {
  goods: [],
  setGoods: () => {}
}

interface Props {
    children: React.ReactNode;
}

export const ShopContext = React.createContext(defaultValue);

export const ShopContextProvider: React.FC<Props> = ({ children }) => {
  const [goods, setGoods] = useState<IGood[]>([]);

  const providerValue: IShopContextState = {
    goods: goods,
    setGoods: setGoods,
  }

  useEffect(() => {
    fetchGoods()
      .then((goods) => {
        setGoods(goods)
      })
  }, []);

  return (
    <ShopContext.Provider value={providerValue}>{children}</ShopContext.Provider>
  );
};

export default ShopContext;
