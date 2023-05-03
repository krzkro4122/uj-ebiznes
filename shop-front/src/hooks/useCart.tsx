import { useState } from "react";
import { IGood } from "../interface";

function useCart() {
  const [cart, setCart] = useState<IGood[]>([]);

  function addGood(good: IGood) {
    setCart([
      ...cart,
      good
    ])
  }

  function removeGood(id: string) {
    const filteredGoods = cart.filter(good => good.id !== id)
    setCart([...filteredGoods])
  }

  return {
    cart,
    addGood,
    removeGood,
  }
}

export default useCart;
