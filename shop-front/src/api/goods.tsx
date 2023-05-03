import axios from "axios";
import { IGood } from "../interface";
import { Item } from "react-use-cart";

const BASE_URL = "http://localhost:9000";

axios.defaults.baseURL = BASE_URL;
axios.defaults.headers.post["Content-Type"] = "application/json;charset=utf-8";
// axios.defaults.headers.post["Access-Control-Allow-Origin"] = 'http://localhost:9000';

const items2goods = (items: Item[]): any[] => {
  return items.map((item) => {
    const good = {
      ID: +item.id,
      Name: item.name,
      Category: item.category,
      CategoryID: item.categoryID,
      Price: item.price,
      Thumbnail: item.thumbnail,
      Quantity: item.quantity!,
      Stock: item.stock,
    };
    return good;
  });
};

export const fetchGoods = async (): Promise<IGood[]> => {
  try {
    const response = await axios.get(`${BASE_URL}/product`);
    return response.data;
  } catch (error) {
    console.error(error);
    return [];
  }
};

export const buyGoods = async (
  items: Item[],
  money: number
): Promise<[any, number]> => {
  let goods = items2goods(items);
  console.log(goods);
  const response = await axios
    .post(`${BASE_URL}/cart/buy`, {
      products: goods,
      payment: money,
    })
    .catch(function (error) {
      console.error(error);
      throw Error(error);
    });
  return [response.data, response.status];
};

export default {
  fetchGoods: fetchGoods,
  buyGoods: buyGoods,
};
