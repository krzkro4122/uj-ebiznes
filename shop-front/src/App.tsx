import React from 'react';
import { BrowserRouter, Link, Route, Routes} from 'react-router-dom';

import { CartProvider } from 'react-use-cart';

import Header from './components/Header'
import { ShopPane } from './components/ShopPane'
import Cart from './components/Cart'
import Welcome from './components/Welcome';
import { ShopContextProvider } from './contexts/ShopContext';

import './App.css';

function App() {
  return (
    <div className="App">
      <div>
          <ShopContextProvider>
            <CartProvider>
              <BrowserRouter>
                <Header>
                  <div className='flex justify-between'>
                    <Link to="/"><button className='rounded-md shadow-md hover:bg-gray-100 active:bg-gray-300 border-gray-400 border-2 p-2'><span>Goofy <b>ahh</b>™️ 🤓</span></button></Link>
                    <Link to="/browse"><button className='rounded-md shadow-md hover:bg-gray-100 active:bg-gray-300 border-gray-400 border-2 p-2'>Browse 🔍</button></Link>
                    <Link to="/cart"><button className='rounded-md shadow-md hover:bg-gray-100 active:bg-gray-300 border-gray-400 border-2 p-2'>Shopping Cart 🛒</button></Link>
                  </div>
                </Header>
                <Routes>
                  <Route path="/" element={<Welcome />}/>
                  <Route path="browse" element={<ShopPane />}/>
                  <Route path="cart" element={<Cart />}/>
                </Routes>
              </BrowserRouter>
          </CartProvider>
        </ShopContextProvider>
      </div>
    </div>
  )
}

export default App;
