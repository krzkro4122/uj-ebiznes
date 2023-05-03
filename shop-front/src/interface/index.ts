import { Item } from "react-use-cart";

type Dispatch<A> = (value: A) => void;

export interface IGood extends Item {
  ID: number,
  Name: string,
  Category: string,
  CategoryID: number,
  Price: number,
  Thumbnail: string,
  Quantity: number,
}

export type IShopContextState = {
  goods: IGood[];
  setGoods: CallableFunction;
}
